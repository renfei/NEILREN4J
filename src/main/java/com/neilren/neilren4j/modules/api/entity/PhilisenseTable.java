package com.neilren.neilren4j.modules.api.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by neil on 10/07/2017.
 */
public class PhilisenseTable extends DataEntity<PhilisenseTable> {
    private Long id;
    private String wechat_name;
    private String kaoqin_username;
    private String kaoqin_pwd;
    private Date add_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWechat_name() {
        return wechat_name;
    }

    public void setWechat_name(String wechat_name) {
        this.wechat_name = wechat_name;
    }

    public String getKaoqin_username() {
        return kaoqin_username;
    }

    public void setKaoqin_username(String kaoqin_username) {
        this.kaoqin_username = kaoqin_username;
    }

    public String getKaoqin_pwd() {
        return kaoqin_pwd;
    }

    public void setKaoqin_pwd(String kaoqin_pwd) {
        this.kaoqin_pwd = kaoqin_pwd;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }
}
