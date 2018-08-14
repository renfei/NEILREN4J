package com.neilren.neilren4j.entity;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Link
 * @Description TODO
 * @Date 2018/7/18 16:44
 */
public class Link {
    private String href;
    private String text;
    private String target;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
