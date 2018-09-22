package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TWorstPasswd implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private Long id;

    private String passwd;

    private String md5;

    private String sha1;

    private String sha224;

    private String sha256;

    private String sha384;

    private String sha512;

    private Integer state;
}