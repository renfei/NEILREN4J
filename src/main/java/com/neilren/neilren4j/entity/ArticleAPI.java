package com.neilren.neilren4j.entity;

import lombok.Data;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ArticleAPI
 * @Description TODO
 * @Date 2018/7/21 12:38
 */
@Data
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
}
