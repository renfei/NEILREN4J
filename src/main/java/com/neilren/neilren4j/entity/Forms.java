package com.neilren.neilren4j.entity;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Forms
 * @Description 友情链接申请表单实体类
 * @Date 2018/8/6 22:45
 */
public class Forms {
    private String link;
    private String domain;
    private String sitename;
    private String email;
    private String qq;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        this.domain = link;
        if (domain.indexOf("http://") != -1) {
            domain = domain.replace("http://", "");
        } else if (domain.indexOf("https://") != -1) {
            domain = domain.replace("https://", "");
        }
        if (domain.indexOf("/") != -1) {
            domain = domain.substring(0, domain.indexOf("/"));
        }
        if (link.indexOf("http://") != -1) {
            this.link = "http://" + domain + "/";
        } else if (link.indexOf("https://") != -1) {
            this.link = "https://" + domain + "/";
        }
    }

    public String getDomain() {
        return domain;
    }

//    public void setDomain(String domain) {
//        this.domain = domain;
//    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
