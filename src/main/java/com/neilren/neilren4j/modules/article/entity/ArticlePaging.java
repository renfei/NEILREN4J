package com.neilren.neilren4j.modules.article.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

/**
 * Created by neil on 14/06/2017.
 */
public class ArticlePaging extends DataEntity<ArticlePaging> {
    private String name;
    private int index;

    public ArticlePaging() {
    }

    public ArticlePaging(String Name, int Index) {
        this.name = Name;
        this.index = Index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {

        return name;
    }

    public int getIndex() {
        return index;
    }
}
