package com.neilren.neilren4j.dbentity;

import lombok.Data;

@Data
public class TIp {
    private Long startip;

    private Long endip;

    private String country;

    private String local;
}