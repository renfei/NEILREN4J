package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.common.util.AliyunGreenWebUtil;
import com.neilren.neilren4j.common.util.DomainUtils;
import com.neilren.neilren4j.common.util.EmailUtils;
import com.neilren.neilren4j.common.util.HttpsClientUtils;
import com.neilren.neilren4j.dao.TFrielinkMapper;
import com.neilren.neilren4j.dbentity.TFrielink;
import com.neilren.neilren4j.entity.AliyunGreenWeb;
import com.neilren.neilren4j.entity.EmailObject;
import com.neilren.neilren4j.entity.Forms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName FrielinkService
 * @Description TODO
 * @Date 2018/8/6 21:12
 */
@Service
public class FrielinkService extends BasePageService {
    @Autowired
    private TFrielinkMapper frielinkMapper;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private HttpsClientUtils httpsClientUtils;

    /**
     * 获取有效的友情链接
     *
     * @return
     */
    public List<TFrielink> getValidFrieLinks() {
        return frielinkMapper.selectValidFrieLinks();
    }

    public TFrielink getFrieLinkByDomain(String domain) {
        return frielinkMapper.selectFrieLinkByDomain(domain);
    }

    /**
     * 保存友情链接到数据库
     *
     * @param forms
     * @return
     */
    public TFrielink saveFrieLinkByForm(Forms forms) {
        TFrielink frielink = new TFrielink();
        frielink.setLink(forms.getLink());
        frielink.setSitename(forms.getSitename());
        frielink.setDomain(forms.getDomain());
        frielink.setEmail(forms.getEmail());
        frielink.setQq(forms.getQq());
        frielink.setAddDate(new Date());
        frielink.setState(0);
        frielink.setBlackWhite(0);
        frielink.setRemark("自助申请");
        frielinkMapper.insertFrieLink(frielink);
        return frielink;
    }

    /**
     * 友情链接首次审核线程启动
     *
     * @param frielink
     */
    public void firstAudit(TFrielink frielink) {
        FirstFrielinkAuditThread firstAuditThread = new FirstFrielinkAuditThread(frielink);
        firstAuditThread.start();
    }

    /**
     * 友情链接首次审核逻辑，提供给审核线程使用
     *
     * @param frielink
     */
    public void firstAuditThread(TFrielink frielink) {

    }

    /**
     * 友情链接定期自动巡检
     */
    @Scheduled(cron = "0 25 3 * * ?")
    void regularVisit() {
        System.out.println("自动定时执行开始");
        //获取正在展示的友情链接
        List<TFrielink> showingFrielink = frielinkMapper.selectShowingFrieLinks();
        for (TFrielink frielink : showingFrielink) {
            //检查是否有回链
            try {
                String html = httpsClientUtils.getPageHtml(frielink.getLink());
                if (html != null) {
                    if (html.indexOf("https://www.neilren.com") == -1 && html.indexOf("https://neilren.com") == -1 &&
                            html.indexOf("http://www.neilren.com") == -1 && html.indexOf("http://neilren.com") == -1) {
                        //未检测到回链
                        frielink.setRemark("友情链接自动审查：未检测到回链");
                        frielink.setState(0);
                        frielink.setLastCheckTime(new Date());
                        frielinkMapper.updateByFrieLink(frielink);
                        //发送通知邮件
                        sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                                "友情链接自动审查时被隐藏的通知：友情链接自动审查；未检测到贵站的回链，链接被隐藏",
                                "<p style=\"margin: 0;text-indent: 2em;\">很遗憾的通知您，贵站[" + frielink.getSitename() +
                                        "(" + frielink.getLink() + ")]的友情链接自动审查暂时未能通过，未能通过的原因是：</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">未能在贵站首页检测到我们的回链，您可能删除了我们的链接，我们会每天自动检测，待检测到您的回链后，将自动放出您的链接。</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">请添加我们的链接：https://www.neilren.com/ 任霏博客网</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                        continue;
                    }
                }
                //检查网站内容合法性
                if (html != null && html != "") {
                    AliyunGreenWebUtil aliyunGreenWebUtil = new AliyunGreenWebUtil();
                    List<String> htmls = new ArrayList<String>();
                    if (html.length() > 3999) {
                        while (html.length() > 3999) {
                            htmls.add(html.substring(0, 3999));
                            html = html.substring(3999, html.length() - 1);
                        }
                    } else {
                        htmls.add(html);
                    }
                    boolean greenWeb = true;
                    String greenWeb_type = "";
                    for (String htmltemp : htmls) {
                        List<AliyunGreenWeb> aliyunGreenWebs = aliyunGreenWebUtil.TextScanSample(htmltemp);
                        for (AliyunGreenWeb obj : aliyunGreenWebs) {
                            if (obj.getSuggestion().equals("block")) {
                                if (obj.getLabel() == "politics" || obj.getLabel() == "terrorism"
                                        || obj.getLabel() == "porn" || obj.getLabel() == "contraband") {
                                    //命中检测
                                    greenWeb = false;
                                    greenWeb_type = obj.getLabelZh();
                                }
                            }
                        }
                    }
                    if (!greenWeb) {
                        frielink.setRemark("友情链接自动审查：网站内容检测到非法内容");
                        frielink.setState(0);
                        frielink.setLastCheckTime(new Date());
                        frielinkMapper.updateByFrieLink(frielink);
                        //发送通知邮件
                        sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                                "友情链接自动审查时被隐藏的通知：友情链接自动审查；网站内容检测到非法内容，链接被隐藏",
                                "<p style=\"margin: 0;text-indent: 2em;\">很遗憾通知您，贵站[" + frielink.getSitename() +
                                        "(" + frielink.getLink() + ")]申请的友情链接自动审查暂时未能通过，未能通过的原因是：</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">在贵站首页内容检测时发现非法内容，类型为【" + greenWeb_type +
                                        "】，请您自查您的内容修复，我们会每天自动检测，待检测到您的内容合法后，将自动放出您的链接。</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //获取隐藏的友情链接
        List<TFrielink> hideFrielink = frielinkMapper.selectHideFrieLinks();
        for (TFrielink frielink : hideFrielink) {
            boolean pass = false;
            //检查注册时间是否大于六个月
            try {
                String[] dom = domainUtils.splitDomain(frielink.getDomain());
                if (dom[dom.length - 1].equals("com") || dom[dom.length - 1].equals("cn") ||
                        dom[dom.length - 1].equals("net") || dom[dom.length - 1].equals("org")) {
                    Date registerDate = domainUtils.getRegisterDate(frielink.getDomain());
                    if (registerDate != null) {
                        Date nowDate = new Date();
                        long diff = nowDate.getTime() - registerDate.getTime();
                        long days = diff / (1000 * 60 * 60 * 24);
                        if (days > 180) {
                            //注册时间距离现在满足六个月
                            pass = true;
                        }
                    }
                } else pass = true;
                //检查是否有回链
                if (pass) {
                    String html = httpsClientUtils.getPageHtml(frielink.getLink());
                    if (html != null && html != "") {
                        if (html.indexOf("https://www.neilren.com") != -1 || html.indexOf("https://neilren.com") != -1 ||
                                html.indexOf("http://www.neilren.com") != -1 || html.indexOf("http://neilren.com") != -1) {
                            //检测到回链
                            //检查网站内容合法性
                            AliyunGreenWebUtil aliyunGreenWebUtil = new AliyunGreenWebUtil();
                            List<String> htmls = new ArrayList<String>();
                            while (html.length() > 3999) {
                                htmls.add(html.substring(0, 3999));
                                html = html.substring(3999, html.length() - 1);
                            }
                            boolean greenWeb = true;
                            String greenWeb_type = "";
                            for (String htmltemp : htmls) {
                                List<AliyunGreenWeb> aliyunGreenWebs = aliyunGreenWebUtil.TextScanSample(htmltemp);
                                for (AliyunGreenWeb obj : aliyunGreenWebs) {
                                    if (obj.getSuggestion().equals("block")) {
                                        if (obj.getLabel() == "politics" || obj.getLabel() == "terrorism"
                                                || obj.getLabel() == "porn" || obj.getLabel() == "contraband") {
                                            //命中检测
                                            greenWeb = false;
                                        }
                                    }
                                }
                            }
                            if (greenWeb) {
                                frielink.setBlackWhite(0);
                                frielink.setRemark("友情链接自动审查：自动审查通过");
                                frielink.setState(1);
                                frielink.setLastCheckTime(new Date());
                                frielinkMapper.updateByFrieLink(frielink);
                                //发送通知邮件
                                sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                                        "友情链接自动审查通过的通知：友情链接自动审查；自动审核通过",
                                        "<p style=\"margin: 0;text-indent: 2em;\">很高兴的通知您，贵站[" + frielink.getSitename() +
                                                "(" + frielink.getLink() + ")]申请的友情链接自动审查通过！</p>" +
                                                "<p style=\"margin: 0;text-indent: 2em;\">我们会每天自动巡查友情链接，请您注意：</p>" +
                                                "<p style=\"margin: 0;text-indent: 2em;\">1、确保网站存活，并且首页存在我们的链接。</p>" +
                                                "<p style=\"margin: 0;text-indent: 2em;\">2、确保网站首页内容没有违法敏感内容，检测到暴恐涉政，色情等内容我们会自动隐藏您的链接。</p>" +
                                                "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("自动定时执行结束");
    }

    private void sendEmail(TFrielink frielink, String title, String Sabstract, String content) {
        EmailObject emailObject = new EmailObject();
        emailObject.setName(frielink.getSitename());
        emailObject.setToEmail(frielink.getEmail());
        emailObject.setEmailSubject(title);
        emailObject.setEvent("友情链接自动审查事件");
        emailObject.setSecurityrating("非涉密内容");
        emailObject.setSabstract(Sabstract);
        emailObject.setEmailContent(content);
        try {
            emailUtils.sendHtmlEmail(emailObject);
        } catch (Exception e) {

        }
    }

    /**
     * 首次友情链接审核线程
     */
    private class FirstFrielinkAuditThread extends Thread {
        private FrielinkService frielinkService = new FrielinkService();
        private TFrielink frielink;

        /**
         * 构造函数
         *
         * @param frielink 首次审核的友情链接
         */
        FirstFrielinkAuditThread(TFrielink frielink) {
            this.frielink = frielink;
        }

        @Override
        public void run() {
            boolean isBlack = false;
            //是否在黑名单中
            List<TFrielink> blackFrielinks = frielinkMapper.selectBlackFrieLinks();
            for (TFrielink blackFrielink : blackFrielinks) {
                if (blackFrielink.getLink().equals(frielink.getLink())) {
                    //匹配到黑名单
                    isBlack = true;
                    break;
                }
                if (blackFrielink.getDomain().equals(frielink.getDomain())) {
                    //匹配到黑名单
                    isBlack = true;
                    break;
                }
                if (blackFrielink.getEmail().equals(frielink.getEmail())) {
                    //匹配到黑名单
                    isBlack = true;
                    break;
                }
                if (blackFrielink.getQq().equals(frielink.getQq())) {
                    //匹配到黑名单
                    isBlack = true;
                    break;
                }
            }
            if (isBlack) {
                //加入黑名单，保存
                frielink.setBlackWhite(-1);
                frielink.setRemark("首次审核：匹配到黑名单，驳回申请");
                frielink.setState(-100);
                frielink.setLastCheckTime(new Date());
                frielinkMapper.updateByFrieLink(frielink);
                //发送通知邮件
                sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                        "友情链接自助申请被驳回的通知：首次审核；匹配到黑名单，驳回申请",
                        "<p style=\"margin: 0;text-indent: 2em;\">很遗憾的通知您，贵站[" + frielink.getSitename() +
                                "(" + frielink.getLink() + ")]申请的友情链接自动审查未能通过，未能通过的原因是：</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">您的【网站/邮箱/QQ】存在于我们的合作黑名单中。</p>" +
                                "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                return;
            }
            //检查注册时间是否大于六个月
            try {

                Date registerDate = domainUtils.getRegisterDate(frielink.getDomain());
                if (registerDate != null) {
                    Date nowDate = new Date();
                    long diff = nowDate.getTime() - registerDate.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (days <= 180) {
                        //注册时间距离现在不足六个月
                        frielink.setRemark("首次审核：域名注册时间不足六个月");
                        frielink.setState(0);
                        frielink.setLastCheckTime(new Date());
                        frielinkMapper.updateByFrieLink(frielink);
                        //发送通知邮件
                        sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                                "友情链接自助申请暂时被隐藏的通知：首次审核；域名注册时间不足六个月，链接被隐藏",
                                "<p style=\"margin: 0;text-indent: 2em;\">很高兴通知您，贵站[" + frielink.getSitename() +
                                        "(" + frielink.getLink() + ")]申请的友情链接自动审查暂时未能通过，未能通过的原因是：</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">您的域名注册时间不足六个月，暂时不能放出，我们的程序会每天自动审查，待您满足注册时间的时候，会自动放出，您可以继续保留我们的链接，方便我们程序在满足条件时放出您的链接。</p>" +
                                        "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //检查是否有回链
            try {
                String html = httpsClientUtils.getPageHtml(frielink.getLink());
                if (html != null && html != "") {
                    if (html.indexOf("https://www.neilren.com") == -1 && html.indexOf("https://neilren.com") == -1) {
                        if (html.indexOf("http://www.neilren.com") == -1 && html.indexOf("http://neilren.com") == -1) {
                            //未检测到回链
                            frielink.setRemark("首次审核：未检测到回链");
                            frielink.setState(0);
                            frielink.setLastCheckTime(new Date());
                            frielinkMapper.updateByFrieLink(frielink);
                            //发送通知邮件
                            sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                                    "友情链接自助申请暂时被隐藏的通知：首次审核；未检测到贵站的回链，链接被隐藏",
                                    "<p style=\"margin: 0;text-indent: 2em;\">很高兴通知您，贵站[" + frielink.getSitename() +
                                            "(" + frielink.getLink() + ")]申请的友情链接自动审查暂时未能通过，未能通过的原因是：</p>" +
                                            "<p style=\"margin: 0;text-indent: 2em;\">未能在贵站首页检测到我们的回链，您可能还没有添加我们的链接，我们会每天自动检测，待检测到您的回链后，将自动放出您的链接。</p>" +
                                            "<p style=\"margin: 0;text-indent: 2em;\">请添加我们的链接：https://www.neilren.com/ 任霏博客网</p>" +
                                            "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                            return;
                        }
                    }
                }
                //检查网站内容合法性
                if (html != null && html != "") {
                    AliyunGreenWebUtil aliyunGreenWebUtil = new AliyunGreenWebUtil();
                    List<String> htmls = new ArrayList<String>();
                    while (html.length() > 3999) {
                        htmls.add(html.substring(0, 3999));
                        html = html.substring(3999, html.length() - 1);
                    }
                    for (String htmltemp : htmls) {
                        List<AliyunGreenWeb> aliyunGreenWebs = aliyunGreenWebUtil.TextScanSample(htmltemp);
                        for (AliyunGreenWeb obj : aliyunGreenWebs) {
                            if (obj.getSuggestion().equals("block")) {
                                if (obj.getLabel() == "politics" || obj.getLabel() == "terrorism"
                                        || obj.getLabel() == "porn" || obj.getLabel() == "contraband") {
                                    //命中检测
                                    frielink.setRemark("首次审核：网站内容检测到非法内容");
                                    frielink.setState(0);
                                    frielink.setLastCheckTime(new Date());
                                    frielinkMapper.updateByFrieLink(frielink);
                                    //发送通知邮件
                                    sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                                            "友情链接自助申请暂时被隐藏的通知：首次审核；网站内容检测到非法内容，链接被隐藏",
                                            "<p style=\"margin: 0;text-indent: 2em;\">很高兴通知您，贵站[" + frielink.getSitename() +
                                                    "(" + frielink.getLink() + ")]申请的友情链接自动审查暂时未能通过，未能通过的原因是：</p>" +
                                                    "<p style=\"margin: 0;text-indent: 2em;\">在贵站首页内容检测时发现非法内容，类型为【" + obj.getLabelZh() +
                                                    "】，请您自查您的内容修复，我们会每天自动检测，待检测到您的内容合法后，将自动放出您的链接。</p>" +
                                                    "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
                                    return;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            //更新数据库
            frielink.setRemark("首次审核：通过");
            frielink.setState(1);
            frielink.setLastCheckTime(new Date());
            frielinkMapper.updateByFrieLink(frielink);
            //发送通知邮件
            sendEmail(frielink, "关于NEILRE.NCOM的友情链接通知",
                    "友情链接自助申请通过的通知：首次审核；审核通过",
                    "<p style=\"margin: 0;text-indent: 2em;\">很高兴通知您，贵站[" + frielink.getSitename() +
                            "(" + frielink.getLink() + ")]申请的友情链接自动审查已经通过！</p>" +
                            "<p style=\"margin: 0;text-indent: 2em;\">我们会每天自动巡查友情链接，请您注意：</p>" +
                            "<p style=\"margin: 0;text-indent: 2em;\">1、确保网站存活，并且首页存在我们的链接。</p>" +
                            "<p style=\"margin: 0;text-indent: 2em;\">2、确保网站首页内容没有违法敏感内容，检测到暴恐涉政，色情等内容我们会自动隐藏您的链接。</p>" +
                            "<p style=\"margin: 0;text-indent: 2em;\">如果您对此有疑问，请发送邮件到 ren.fei.cn@gmail.com 进行人工处理。</p>");
            return;
        }
    }
}
