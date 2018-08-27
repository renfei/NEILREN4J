package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.util.Date;

@Data
public class TFrielink {
    private Long id;

    private Date addDate;

    private Integer state;

    private Integer blackWhite;

    private Date lastCheckTime;

    private String sitename;

    private String link;

    private String domain;

    private String email;

    private String qq;

    private String remark;
}