package com.neilren.neilren4j.entity;

import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Search
 * @Description TODO
 * @Date 2018/7/27 16:18
 */
public class Search {
    private Long id;
    private String title;
    private String content;
    private int pageview;
    private int typeid;
    private Date date;
    private String searchtime;
    private int total;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageview() {
        return pageview;
    }

    public void setPageview(int pageview) {
        this.pageview = pageview;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSearchtime() {
        return searchtime;
    }

    public void setSearchtime(String searchtime) {
        this.searchtime = searchtime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
