package com.neilren.neilren4j.modules.article.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

public class ArticleCategory extends DataEntity<ArticleCategory> {
    private Long id;

    private Long articleId;

    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}