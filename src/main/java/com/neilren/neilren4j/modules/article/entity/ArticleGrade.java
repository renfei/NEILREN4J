package com.neilren.neilren4j.modules.article.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;

public class ArticleGrade extends DataEntity<ArticleGrade> {
    private Long id;

    private Long articleId;

    private Integer grade;

    private Date addDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}