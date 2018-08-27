package com.neilren.neilren4j.entity.aliopenserach;

import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ResultsItems
 * @Description TODO
 * @Date 2018/7/27 16:15
 */
@Data
public class ResultsItems implements Serializable {
    private String mainid;
    private String title;
    private String content;
    private Date timestamp;
    private int typeid;
    private int pageview;
    private int review;
    private String id;
    private String index_name;

    public void setTimestamp(Long timestamp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(timestamp);
        this.timestamp = format.parse(d);
    }
}
