package com.neilren.neilren4j.common.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Neilren4jConfig
 * @Description TODO
 * @Date 2018/7/16 17:11
 */

@Component
@ConfigurationProperties(prefix = "neilren")
public class Neilren4jConfig {
    private String version;
    private String host;
    private String googletotpseed;
    private String googletotpissuer;
    private List<String> pagecss;
    private List<String> pagejss;
    private Baidu baidu;
    private Aliyun aliyun;
    private Email email;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getPagecss() {
        return pagecss;
    }

    public void setPagecss(List<String> pagecss) {
        this.pagecss = pagecss;
    }

    public List<String> getPagejss() {
        return pagejss;
    }

    public void setPagejss(List<String> pagejss) {
        this.pagejss = pagejss;
    }

    public String getGoogletotpseed() {
        return googletotpseed;
    }

    public void setGoogletotpseed(String googletotpseed) {
        this.googletotpseed = googletotpseed;
    }

    public String getGoogletotpissuer() {
        return googletotpissuer;
    }

    public void setGoogletotpissuer(String googletotpissuer) {
        this.googletotpissuer = googletotpissuer;
    }

    public Baidu getBaidu() {
        return baidu;
    }

    public void setBaidu(Baidu baidu) {
        this.baidu = baidu;
    }

    public Aliyun getAliyun() {
        return aliyun;
    }

    public void setAliyun(Aliyun aliyun) {
        this.aliyun = aliyun;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
