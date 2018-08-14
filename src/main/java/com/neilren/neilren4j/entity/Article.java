package com.neilren.neilren4j.entity;

import com.neilren.neilren4j.dbentity.TArticle;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Article
 * @Description TODO
 * @Date 2018/7/21 10:58
 */
public class Article extends TArticle {
    private String enName;
    private String zh_name;

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
