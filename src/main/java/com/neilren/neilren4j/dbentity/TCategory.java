package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TCategory implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private Long id;

    private String enName;

    private String zhName;

    private String describe;
}