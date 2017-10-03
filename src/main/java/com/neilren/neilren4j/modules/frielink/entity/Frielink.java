package com.neilren.neilren4j.modules.frielink.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;

/**
 * 友情链接实体类
 */
public class Frielink extends DataEntity<Frielink> {
    private Long id;
    private String link;
    private Date add_date;
    private String sitename;
    private String domain;
    private int state;
    private String email;
    private String qq;
    private int black_white;
    private String remark;
    private Date last_check_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public int getBlack_white() {
        return black_white;
    }

    public void setBlack_white(int black_white) {
        this.black_white = black_white;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLast_check_time() {
        return last_check_time;
    }

    public void setLast_check_time(Date last_check_time) {
        this.last_check_time = last_check_time;
    }
}
