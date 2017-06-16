package com.neilren.neilren4j.modules.article.entity;

/**
 * Created by neil on 13/06/2017.
 */
public class ArticleArchives {
    private String DateYmd;
    private int Number;

    public void setDateYmd(String dateName) {
        DateYmd = dateName;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getDateYmd() {

        return DateYmd;
    }

    public int getNumber() {
        return Number;
    }
}
