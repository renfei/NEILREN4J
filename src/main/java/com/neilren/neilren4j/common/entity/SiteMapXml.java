package com.neilren.neilren4j.common.entity;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neil on 29/06/2017.
 */
public class SiteMapXml {
    private String loc;
    private Changefreq changefreq;
    private float priority;
    private Date lastmod;

    public String getLastmod() {
        if (lastmod == null)
            return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastmod);
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
