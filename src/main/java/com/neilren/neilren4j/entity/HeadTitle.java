package com.neilren.neilren4j.entity;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName HeadTitle
 * @Description TODO
 * @Date 2018/8/14 17:16
 */
public class HeadTitle {
    private String title;
    private String description;
    private String keyword;

    public HeadTitle(String title, String description, String keyword) {
        this.title = title;
        this.description = description;
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
