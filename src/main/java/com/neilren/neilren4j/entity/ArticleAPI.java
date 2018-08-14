package com.neilren.neilren4j.entity;

import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ArticleAPI
 * @Description TODO
 * @Date 2018/7/21 12:38
 */
public class ArticleAPI {
    private Long id;
    private String title;
    private String img;
    private String zh_name;

    public ArticleAPI() {
    }

    public ArticleAPI(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.img = article.getImg();
        this.zh_name = article.getZh_name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }
}
