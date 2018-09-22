package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TLogSendEmail implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private Long id;

    private Date senddate;

    private String toemail;

    private String subject;

    private String content;
}