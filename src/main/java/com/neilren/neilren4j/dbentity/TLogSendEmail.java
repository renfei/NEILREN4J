package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.util.Date;

@Data
public class TLogSendEmail {
    private Long id;

    private Date senddate;

    private String toemail;

    private String subject;

    private String content;
}