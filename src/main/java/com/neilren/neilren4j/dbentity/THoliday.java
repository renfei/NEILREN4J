package com.neilren.neilren4j.dbentity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class THoliday implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private Long id;

    @DateTimeFormat
    private Date startDate;

    @DateTimeFormat
    private Date endDate;

    private String color;

    private String style;

    private String text;
}