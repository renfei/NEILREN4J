package com.neilren.neilren4j.common.utils;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.entity.EmailObject;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    // 邮件发送协议
    private final static String PROTOCOL = "smtp";
    // SMTP邮件服务器
    private final static String HOST = Global.getConfig("email.smtp");
    // SMTP邮件服务器默认端口
    private final static String PORT = Global.getConfig("email.port");
    // 是否要求身份认证
    private final static String IS_AUTH = "true";
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = Global.getConfig("email.debug");
    // 发件人
    private static String from = Global.getConfig("email.from");
    // SSL
    private static String SSL = Global.getConfig("email.ssl");
    // 初始化连接邮件服务器的会话信息
    private static Properties props = null;

    static {
        props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.port", PORT);
        props.setProperty("mail.smtp.auth", IS_AUTH);
        props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
        props.setProperty("mail.smtp.ssl.enable", SSL);
    }

    public void sendHtmlEmail(EmailObject email) throws Exception {
        // 创建Session实例对象
        Session session = Session.getInstance(props, new EmailAuthenticator());

        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置邮件主题
        message.setSubject(email.getEmailSubject());
        // 设置发送人
        message.setFrom(new InternetAddress(from));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置收件人
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getToEmail()));
        // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
        message.setContent(email.getEmailContent(), "text/html;charset=utf-8");
        // 保存并生成最终的邮件内容
        message.saveChanges();
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 邮件服务器认证
     */
    static class EmailAuthenticator extends Authenticator {
        private String username = Global.getConfig("email.username");
        private String password = Global.getConfig("email.password");

        public EmailAuthenticator() {
            super();
        }

        public EmailAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(username, password);
        }
    }
}
