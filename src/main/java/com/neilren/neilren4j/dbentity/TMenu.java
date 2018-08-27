package com.neilren.neilren4j.dbentity;

import lombok.Data;

@Data
public class TMenu {
    private Long id;

    private String name;

    private String url;

    private Integer sort;

    private Boolean isShow;
}