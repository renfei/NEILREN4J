package com.neilren.neilren4j.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Search
 * @Description TODO
 * @Date 2018/7/27 16:18
 */
@Data
public class Search {
    private Long id;
    private String title;
    private String content;
    private int pageview;
    private int typeid;
    private Date date;
    private String searchtime;
    private int total;
}
