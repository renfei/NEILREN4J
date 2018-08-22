package com.neilren.neilren4j.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SiteMapXml
 * @Description TODO
 * @Date 2018/7/31 18:15
 */
public class SiteMapXml implements Serializable {
    private static final long serialVersionUID = 1L;
    private String loc;
    private Changefreq changefreq;
    private float priority;
    private Date lastmod;

    public String getLastmod() throws ParseException {
        if (lastmod == null)
            return "";
        String str = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(lastmod);
        return str.substring(0, str.length() - 2) + ":00";
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public SiteMapXml(String loc, Changefreq changefre, String priority) {
        this.setChangefreq(changefre);
        this.setLoc(loc);
        this.setPriority(Float.parseFloat(priority));
    }

    public SiteMapXml(String loc, Changefreq changefre, String priority, Date lastmod) {
        this.setChangefreq(changefre);
        this.setLoc(loc);
        this.setPriority(Float.parseFloat(priority));
        this.setLastmod(lastmod);
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getChangefreq() {
        return changefreq.toString();
    }

    public void setChangefreq(Changefreq changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return String.valueOf(priority);
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }
}