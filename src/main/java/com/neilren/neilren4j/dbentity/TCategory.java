package com.neilren.neilren4j.dbentity;

import lombok.Data;

@Data
public class TCategory {
    private Long id;

    private String enName;

    private String zhName;

    private String describe;
}