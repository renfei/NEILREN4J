package com.neilren.neilren4j.modules.api.service;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.utils.StringUtils;
import com.neilren.neilren4j.modules.api.dao.WeChatMagDao;
import com.neilren.neilren4j.modules.api.entity.Article;
import com.neilren.neilren4j.modules.api.entity.WeChatMessage;
import com.neilren.neilren4j.modules.api.entity.WeChatMsg;
import com.neilren.neilren4j.modules.api.entity.WeChatReply;
import com.neilren.neilren4j.modules.search.entity.Results;
import com.neilren.neilren4j.modules.search.service.SearchService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 微信公众号服务
 */
@Service
public class WeChatService {
    private String Token;
    private String EncodingAESKey;
    @Autowired
    private SearchService searchService;
    @Autowired
    private WeChatMagDao weChatMagDao;
    @Autowired
    private PhilisenseKaoQinService philisenseKaoQinService;

    WeChatService() {
        this.Token = Global.getConfig("wechat.token");
        this.EncodingAESKey = Global.getConfig("wechat.encodingaeskey");
    }

    public boolean checkSignature(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if (signature != null && timestamp != null && nonce != null) {
            String[] strSet = new String[]{Token, timestamp, nonce};
            java.util.Arrays.sort(strSet);
            String key = "";
            for (String string : strSet) {
                key = key + string;
            }
            String pwd = sha1(key);
            return pwd.equals(signature);
        } else {
            return false;
        }
    }

    /**
     * sha1加密算法
     *
     * @param key 需要加密的字符串
     * @return 加密后的结果
     */
    public static String sha1(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(key.getBytes());
            String pwd = new BigInteger(1, md.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
            return key;
        }
    }

    public WeChatReply responseMessage(HttpServletRequest request) {
        Map<String, String> requestMap = parseXml(request);
        WeChatMessage message = mapToMessage(requestMap);
        //保存接受消息到数据库
        WeChatMsg weChatMsg = new WeChatMsg();
        weChatMsg.setRecording_time(new Date());
        weChatMsg.setType(0);
        weChatMsg.setFrom_user_name(message.getFromUserName());
        weChatMsg.setCreate_time(message.getCreateTime().getTime());
        weChatMsg.setMsg_type(message.getMsgType());
        if (message.getMsgType().equals(WeChatMessage.TEXT))
            weChatMsg.setContent(message.getContent());
        else//如果是语音识别类型的消息，则获取Recognitio字段
            weChatMsg.setContent(StringUtils.removePunctuation(message.getRecognition()));
        weChatMsg.setMsg_id(Long.parseLong(message.getMsgId()));
        weChatMsg.setOriginal(requestMap.toString());
        weChatMagDao.insert(weChatMsg);
        //保存结束
        //******************考勤接入*********************//
        if (weChatMsg.getContent().substring(0, 2).equals("考勤") && message.getMsgType().equals(WeChatMessage.TEXT)) {
            return philisenseKaoQinService.philisenseKaoQin(message, weChatMsg);
        }
        //******************考勤接入*********************//
        String replyContent = WeChatReply.WELCOME_CONTENT;
        String type = message.getMsgType();
        //拼装回复消息
        WeChatReply reply = new WeChatReply();
        reply.setToUserName(message.getFromUserName());
        reply.setFromUserName(message.getToUserName());
        reply.setCreateTime(new Date());
        //接收语音消息，需要在公众号平台开启语音识别
        if (type.equals(WeChatMessage.TEXT) || type.equals(WeChatMessage.VOICE)) {//仅处理文本回复内容
            String content;//消息内容
            if (type.equals(WeChatMessage.TEXT))
                content = message.getContent();
            else content = message.getVOICE();
            Results results = null;
            try {
                //调用搜索服务
                results = searchService.Search(content, 1);
            } catch (Exception e) {
                reply.setMsgType(WeChatReply.TEXT);
                reply.setContent(WeChatReply.ERROR_CONTENT);
            }
            if (results != null && results.getItems().size() > 0) {
                List<Article> articles = new ArrayList<Article>();
                //循环装载5个搜索结果
                for (int i = 0; i < results.getItems().size() && i < 5; i++) {
                    Article article = new Article();
                    article.setTitle(results.getItems().get(i).getTitle());
                    article.setDescription(results.getItems().get(i).getContent());
                    article.setUrl(Global.getConfig("neilren.site") + "/Article/" + results.getItems().get(i).getId());
                    articles.add(article);
                }
                reply.setArticles(articles);
                reply.setMsgType(WeChatReply.NEWS);
                reply.setArticleCount(articles.size());
            } else {
                //什么也没搜索到
                reply.setMsgType(WeChatReply.TEXT);
                if (message.getMsgType().equals(WeChatMessage.TEXT))
                    reply.setContent("根据您发来的【" + content + "】没有搜索到任何博文，陛下换个词试试？");
                else
                    reply.setContent("根据您发来的【" + message.getRecognition() + "】没有搜索到任何博文，陛下换个词试试？");
            }
        } else {
            reply.setMsgType(WeChatReply.TEXT);
            reply.setContent("NEILREN.COM 的微信模块暂时只能处理文本类消息哦，我们会积极改进的。");
        }
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
        weChatMsgReply.setOriginal(replyToXml(reply));
        weChatMagDao.insert(weChatMsgReply);
        //保存结束
        return reply;
    }

    /**
     * 解析request中的xml 并将数据存储到一个Map中返回
     *
     * @param request
     */
    public Map<String, String> parseXml(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            InputStream inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            for (Element e : elementList)
                //遍历xml将数据写入map
                map.put(e.getName(), e.getText());
            inputStream.close();
            inputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 存储数据的Map转换为对应的Message对象
     *
     * @param map 存储数据的map
     * @return 返回对应Message对象
     */
    public static WeChatMessage mapToMessage(Map<String, String> map) {
        if (map == null) return null;
        String msgType = map.get("MsgType");
        WeChatMessage message = new WeChatMessage();
        message.setToUserName(map.get("ToUserName"));
        message.setFromUserName(map.get("FromUserName"));
        message.setCreateTime(new Date(Long.parseLong(map.get("CreateTime"))));
        message.setMsgType(msgType);
        message.setMsgId(map.get("MsgId"));
        if (msgType.equals(WeChatMessage.TEXT)) {
            message.setContent(map.get("Content"));
        } else if (msgType.equals(WeChatMessage.IMAGE)) {
            message.setPicUrl(map.get("PicUrl"));
        } else if (msgType.equals(WeChatMessage.LINK)) {
            message.setTitle(map.get("Title"));
            message.setDescription(map.get("Description"));
            message.setUrl(map.get("Url"));
        } else if (msgType.equals(WeChatMessage.LOCATION)) {
            message.setLocationX(map.get("Location_X"));
            message.setLocationY(map.get("Location_Y"));
            message.setScale(map.get("Scale"));
            message.setLabel(map.get("Label"));
        } else if (msgType.equals(WeChatMessage.EVENT)) {
            message.setEvent(map.get("Event"));
            message.setEventKey(map.get("EventKey"));
        } else if (msgType.equals(WeChatMessage.VOICE)) {
            message.setRecognition(map.get("Recognition"));
        }
        return message;
    }

    /**
     * 将回复消息对象转换成xml字符串
     *
     * @param reply 回复消息对象
     * @return 返回符合微信接口的xml字符串
     */
    public String replyToXml(WeChatReply reply) {
        XStream xstream = new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    @SuppressWarnings("unchecked")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        String type = reply.getMsgType();
        if (WeChatReply.TEXT.equals(type)) {
            xstream.omitField(WeChatReply.class, "articles");
            xstream.omitField(WeChatReply.class, "articleCount");
            xstream.omitField(WeChatReply.class, "musicUrl");
            xstream.omitField(WeChatReply.class, "hQMusicUrl");
        } else if (WeChatReply.MUSIC.equals(type)) {
            xstream.omitField(WeChatReply.class, "articles");
            xstream.omitField(WeChatReply.class, "articleCount");
            xstream.omitField(WeChatReply.class, "content");
        } else if (WeChatReply.NEWS.equals(type)) {
            xstream.omitField(WeChatReply.class, "content");
            xstream.omitField(WeChatReply.class, "musicUrl");
            xstream.omitField(WeChatReply.class, "hQMusicUrl");
        }
        xstream.autodetectAnnotations(true);
        xstream.alias("xml", reply.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(reply);
    }
}
