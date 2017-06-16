package com.neilren.neilren4j.modules.article.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;

public class Frielink extends DataEntity<Frielink> {
    private Long id;

    private Date addDate;

    private Integer enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}