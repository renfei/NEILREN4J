package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TFrielink implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
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