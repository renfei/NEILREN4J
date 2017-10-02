package com.neilren.neilren4j.modules.api.service;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.modules.api.dao.WeChatMagDao;
import com.neilren.neilren4j.modules.api.dao.WeChatPhilisenseKaoQinDao;
import com.neilren.neilren4j.modules.api.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by neil on 10/07/2017.
 */
@Service
public class PhilisenseKaoQinService {
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private WeChatPhilisenseKaoQinDao weChatPhilisenseKaoQinDao;
    @Autowired
    private WeChatService weChatService;
    @Autowired
    private WeChatMagDao weChatMagDao;

    private static String PLX = "http://119.40.53.172:7050";
    private String Cookie = "";

    /**
     * 飞利信考勤接入
     *
     * @return
     */
    public WeChatReply philisenseKaoQin(WeChatMessage message, WeChatMsg weChatMsg) {
        String weUserId = weChatMsg.getFrom_user_name();
        String content = weChatMsg.getContent();
        //根据当前微信账号，查找内控账号
        PhilisenseTable neikong = weChatPhilisenseKaoQinDao.selectByWeChat(weUserId);
        WeChatReply reply = new WeChatReply();
        reply.setToUserName(message.getFromUserName());
        reply.setFromUserName(message.getToUserName());
        reply.setCreateTime(new Date());
        reply.setMsgType(WeChatReply.TEXT);
        if (content.length() > 5 && content.substring(0, 6).equals("考勤#绑定#")) {
            //绑定账号
            if (neikong != null) {
                reply.setContent("您的微信账号已经绑定过内控账号【" + neikong.getKaoqin_username()
                        + "】，可直接发送\"考勤\"或者\"考勤#当月\"或者\"考勤#指定#2017-06-01#2017-09-30\"进行考勤查询。如需解绑请发送\"考勤#解绑\"。");
                return returnReply(reply);
            } else {
                String[] arrString = content.split("#");
                if (arrString.length != 4) {
                    reply.setContent("您发送的绑定指令有误，解析失败，请参照格式：考勤#绑定#内控账号#内控密码");
                    return returnReply(reply);
                } else {
                    String neikongzhanghao = arrString[2];
                    String neikongmiam = arrString[3];
                    //对账号密码进行测试
                    try {
                        postLogin(neikongzhanghao, neikongmiam);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                        PhilisenseKaoQin jieguo = postChaxun(neikong, sdf.parse("2017-06-01 00:00:00"), sdf.parse("2017-06-15 00:00:00"));
                        if (jieguo != null && jieguo.getRows().size() > 0) {
                            PhilisenseTable philisenseTable = new PhilisenseTable();
                            philisenseTable.setAdd_date(new Date());
                            philisenseTable.setKaoqin_pwd(neikongmiam);
                            philisenseTable.setKaoqin_username(neikongzhanghao);
                            philisenseTable.setWechat_name(weUserId);
                            weChatPhilisenseKaoQinDao.insert(philisenseTable);
                            reply.setContent("绑定成功！可直接发送\"考勤\"或者\"考勤#当月\"或者\"考勤#指定#2017-06-01#2017-09-30\"进行考勤查询。如需解绑请发送\"考勤#解绑\"。");
                            return returnReply(reply);
                        } else {
                            reply.setContent("绑定失败，根据您提供的内控账号密码，未能成功登陆内控系统，请查证后重试。");
                            return returnReply(reply);
                        }
                    } catch (Exception e) {
                        reply.setContent("绑定失败，系统发生异常，事故现场：" + e.getMessage());
                        return returnReply(reply);
                    }
                }
            }
        }
        if (neikong == null) {
            //发送引导绑定文章
            reply.setMsgType(WeChatReply.NEWS);
            reply.setArticleCount(1);
            List<Article> articles = new ArrayList<Article>();
            Article article = new Article();
            article.setDescription("发送“考勤#绑定#内控账号#内控密码\"，例如：考勤#绑定#zhangsan#123456;发送“考勤”，即可查询当天考勤情况。此微信是为了方便普通员工查询考勤使用，一次绑定，只需发送“考勤”两个字即可快速查看当天考勤情况。");
            article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/TcEHsgQibURFoIwlviaCJ167Ko2icvOWpjh1dQbwTgAfoCRybcyB9I1IGNiavzccc7yYYicJ68C2PVic01R7YaRTd0BQ/0?wx_fmt=jpeg");
            article.setTitle("飞利信内控考勤非官方第三方微信实现");
            article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwOTE4NjU0MA==&mid=2247483657&idx=1&sn=a8c5b8b3dc081de07d6c5667105390b4&chksm=9776fa8aa001739caef7e805d9b81e270fedbdf8b4f58be35d4a513ea680d25eabcaa22e8fb7&mpshare=1&scene=23&srcid=0710dHFR7kfWlhUL4m6axa1d#rd");
            articles.add(article);
            reply.setArticles(articles);
            return returnReply(reply);
        }
        //删除绑定
        if (content.length() > 4 && content.substring(0, 5).equals("考勤#解绑")) {
            //解绑
            if (neikong == null) {
                reply.setContent("解绑失败，您未绑定内控账号。");
                return returnReply(reply);
            } else {
                try {
                    weChatPhilisenseKaoQinDao.deleteByWeChat(weUserId);
                    reply.setContent("解绑成功！期待您的再次回归。绑定请参照格式：考勤#绑定#内控账号#内控密码");
                    return returnReply(reply);
                } catch (Exception e) {
                    reply.setContent("解绑失败，系统发生异常，事故现场：" + e.getMessage());
                    return returnReply(reply);
                }
            }
        }

        if (content.equals("考勤")) {
            //查询当天考勤
            try {
                PhilisenseKaoQin jieguo = postChaxun(neikong, new Date(), new Date());
                if (jieguo != null && jieguo.getRows().size() > 0) {
                    reply.setContent(getString(jieguo));
                    return returnReply(reply);
                } else {
                    reply.setContent("未查询到当天考勤信息");
                    return returnReply(reply);
                }
            } catch (Exception e) {
                reply.setContent("查询失败，系统发生异常，事故现场：" + e.getMessage());
                return returnReply(reply);
            }
        }
        if (content.length() > 4 && (content.substring(0, 5).equals("考勤#当月") || content.substring(0, 5).equals("考勤#本月"))) {
            //查询本月考勤
            // 获取当前年份、月份、日期
            Calendar cale = null;
            cale = Calendar.getInstance();
            // 获取当月第一天和最后一天
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String firstday, lastday;
            // 获取前月的第一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
            firstday = format.format(cale.getTime());
            // 获取前月的最后一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
            lastday = format.format(cale.getTime());
            try {
                PhilisenseKaoQin jieguo = postChaxun(neikong, format.parse(firstday + " 00:00:00"), format.parse(lastday + " 23:59:59"));
                if (jieguo != null && jieguo.getRows().size() > 0) {
                    reply.setContent(getString(jieguo));
                    return returnReply(reply);
                } else {
                    reply.setContent("未查询到本月考勤信息");
                    return returnReply(reply);
                }
            } catch (Exception e) {
                reply.setContent("查询失败，系统发生异常，事故现场：" + e.getMessage());
                return returnReply(reply);
            }
        }
        if (content.length() > 5 && content.substring(0, 6).equals("考勤#指定#")) {
            //查询指定日期
            String[] arrString = content.split("#");
            if (arrString.length != 4) {
                reply.setContent("您发送的绑定指令有误，解析失败，请参照格式：考勤#绑定#2017-6-1#2017-7-31");
                return returnReply(reply);
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    PhilisenseKaoQin jieguo = postChaxun(neikong, format.parse(arrString[2] + " 00:00:00"), format.parse(arrString[3] + " 23:59:59"));
                    if (jieguo != null && jieguo.getRows().size() > 0) {
                        reply.setContent(getString(jieguo));
                        return returnReply(reply);
                    } else {
                        reply.setContent("未查询到指定的考勤信息");
                        return returnReply(reply);
                    }
                } catch (Exception e) {
                    reply.setContent("查询失败，系统发生异常，事故现场：" + e.getMessage());
                    return returnReply(reply);
                }
            }
        }
        reply.setContent("指令未匹配，请重试");
        return returnReply(reply);
    }

    private WeChatReply returnReply(WeChatReply reply) {
        //保存回复消息到数据库
        WeChatMsg weChatMsgReply = new WeChatMsg();
        weChatMsgReply.setRecording_time(new Date());
        weChatMsgReply.setType(1);
        weChatMsgReply.setFrom_user_name(reply.getFromUserName());
        weChatMsgReply.setCreate_time(reply.getCreateTime().getTime());
        weChatMsgReply.setMsg_type(reply.getMsgType());
        if (reply.getContent() == null) {
            if (reply.getArticleCount() > 0)
                weChatMsgReply.setContent(reply.getArticles().toString());
            else
                weChatMsgReply.setContent("");
        } else
            weChatMsgReply.setContent(reply.getContent());
        weChatMsgReply.setMsg_id(0L);
        weChatMsgReply.setOriginal(weChatService.replyToXml(reply));
        weChatMagDao.insert(weChatMsgReply);
        return reply;
    }

    private String getString(PhilisenseKaoQin jieguo) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jieguo.getRows().size(); i++) {
            sb.append(jieguo.getRows().get(i).getUserName() + "\t");
            sb.append(jieguo.getRows().get(i).getsJ0106() + "\t");
            sb.append(jieguo.getRows().get(i).getState() + "\r\n");
            sb.append("最早打卡：" + jieguo.getRows().get(i).getMinSJ0100() + "\r\n");
            sb.append("最晚打卡：" + jieguo.getRows().get(i).getMaxSH0100() + "\r\n");
            sb.append("备注：" + jieguo.getRows().get(i).getSuperimposed() + "\r\n");
            sb.append("------\r\n");
        }
        return sb.toString();
    }

    private PhilisenseKaoQin postChaxun(PhilisenseTable neikong, Date startDate, Date endDate) throws Exception {
        if (this.Cookie.equals("")) {
            postLogin(neikong.getKaoqin_username(), neikong.getKaoqin_pwd());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        URL url = new URL(PLX + "/WebRoot/MySigninSearchAction.do?randid=" + Math.random());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);

        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded; text/html; charset=UTF-8");
        con.setRequestProperty("Cookie", this.Cookie);
        con.setConnectTimeout(1000);
        con.setReadTimeout(3000);
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        osw.write("sJ0106=" + sdf.format(startDate) + "&tameks=" + sdf.format(endDate) + "&state=&page=1&rows=999");
        osw.flush();
        osw.close();
        /////////////
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con
                .getInputStream(), "UTF-8"));
        String temp;
        while ((temp = br.readLine()) != null) {
            buffer.append(temp);
            buffer.append("\n");
        }
        return JSON.parseObject(buffer.toString(), PhilisenseKaoQin.class);
    }

    /**
     * 登陆获取 Cookie
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    private String postLogin(String username, String password) throws Exception {
        String meCookie = null;//(String) memcachedManager.get("PLX_" + username + "_" + password);
        if (meCookie != null && !meCookie.equals("")) {
            this.Cookie = meCookie;
            return meCookie;
        }
        URL url = new URL(PLX + "/WebRoot/LoginAction.do");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);

        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded; text/html; charset=UTF-8");
        con.setConnectTimeout(1000);
        con.setReadTimeout(3000);
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        osw.write("activity=login&userId=" + username + "&password=" + password);
        osw.flush();
        osw.close();
        /////////////
        //从请求中获取cookie列表
        String cookieskey = "Set-Cookie";
        Map<String, List<String>> maps = con.getHeaderFields();
        List<String> coolist = maps.get(cookieskey);
        Iterator<String> it = coolist.iterator();
        StringBuffer sbu = new StringBuffer();
        while (it.hasNext()) {
            sbu.append(it.next() + ";");
        }
        String cookies = sbu.toString();
        cookies = cookies.substring(0, cookies.length() - 1);
        this.Cookie = cookies;
        //memcachedManager.set("PLX_" + username + "_" + password, cookies, 300);
        return cookies;
    }
}
