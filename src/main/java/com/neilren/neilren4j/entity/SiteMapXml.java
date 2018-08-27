package com.neilren.neilren4j.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
@Data
@Slf4j
public class SiteMapXml implements Serializable {
    private static final long serialVersionUID = 1L;
    private String loc;
    private Changefreq changefreq;
    private float priority;
    private Date lastmod;

    public String getLastmod() {
        if (lastmod == null)
            return "";
        String str ="";
        try {
            str = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(lastmod);
            return str.substring(0, str.length() - 2) + ":00";
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return "";
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
}