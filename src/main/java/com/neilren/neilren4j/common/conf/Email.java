package com.neilren.neilren4j.common.conf;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Email
 * @Description TODO
 * @Date 2018/8/6 21:17
 */
public class Email {
    private String smtp;
    private String port;
    private String debug;
    private String from;
    private String ssl;
    private String username;
    private String password;

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSsl() {
        return ssl;
    }

    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
