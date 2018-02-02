package com.neilren.neilren4j.modules.image.entity;

public class Image extends ImageMeta {
    private Long id;

    private Long albumid;

    private Long meteid;

    private String url;

    private String title;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Long albumid) {
        this.albumid = albumid;
    }

    public Long getMeteid() {
        return meteid;
    }

    public void setMeteid(Long meteid) {
        this.meteid = meteid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}