package com.neilren.neilren4j.modules.image.entity;

public class AlbumWithBLOBs extends Album {
    private String title;

    private String cover;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }
}