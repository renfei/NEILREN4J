package com.neilren.neilren4j.common.util;

import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import com.neilren.neilren4j.dao.TLogSendEmailMapper;
import com.neilren.neilren4j.dbentity.TLogSendEmail;
import com.neilren.neilren4j.entity.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName EmailUtils
 * @Description TODO
 * @Date 2018/8/6 21:14
 */
@Service
public class EmailUtils {
    @Autowired
    private Neilren4jConfig neilren4jConfig;
    @Autowired
    private TLogSendEmailMapper logSendEmailMapper;

    public void sendHtmlEmail(EmailObject email) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", neilren4jConfig.getEmail().getSmtp());
        props.setProperty("mail.smtp.port", neilren4jConfig.getEmail().getPort());
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.debug", neilren4jConfig.getEmail().getDebug());
        props.setProperty("mail.smtp.ssl.enable", neilren4jConfig.getEmail().getSsl());
        // 创建Session实例对象
        Session session = Session.getInstance(props, new EmailAuthenticator());

        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置邮件主题
        message.setSubject(email.getEmailSubject(), "utf-8");
        // 设置发送人
        message.setFrom(new InternetAddress(neilren4jConfig.getEmail().getFrom()));
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
        //记录邮件发送日志
        TLogSendEmail sendEmailLog = new TLogSendEmail();
        sendEmailLog.setToemail(email.getToEmail());
        sendEmailLog.setSubject(email.getEmailSubject());
        sendEmailLog.setSenddate(new Date());
        sendEmailLog.setContent(email.getEmailContent());
        logSendEmailMapper.insert(sendEmailLog);
    }

    /**
     * 邮件服务器认证
     */
    class EmailAuthenticator extends Authenticator {
        private String username = neilren4jConfig.getEmail().getUsername();
        private String password = neilren4jConfig.getEmail().getPassword();

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
