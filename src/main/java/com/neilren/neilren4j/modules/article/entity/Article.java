package com.neilren.neilren4j.modules.article.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;

public class Article extends DataEntity<Article> {
    private Long id;

    private Integer articleType;

    private Long views;

    private Date articleDat;

    private Integer articleStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Date getArticleDat() {
        return articleDat;
    }

    public void setArticleDat(Date articleDat) {
        this.articleDat = articleDat;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }
}