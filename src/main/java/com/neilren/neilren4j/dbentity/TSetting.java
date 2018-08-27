package com.neilren.neilren4j.dbentity;

import lombok.Data;

@Data
public class TSetting {
    private Long id;

    private String key;

    private Integer sort;

    private String value;
}