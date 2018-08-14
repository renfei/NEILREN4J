package com.neilren.neilren4j.entity;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Footer
 * @Description TODO
 * @Date 2018/7/18 16:40
 */
public class Footer {
    private List<Link> linkList;
    private FootMenu footMenu;
    private String processed;
    private String calls;

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public FootMenu getFootMenu() {
        return footMenu;
    }

    public void setFootMenu(FootMenu footMenu) {
        this.footMenu = footMenu;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public String getCalls() {
        return calls;
    }

    public void setCalls(String calls) {
        this.calls = calls;
    }

    class FootMenu {

    }
}
