package com.neilren.neilren4j.dbentity;

import lombok.Data;

@Data
public class TArticleCategory {
    private Long id;

    private Long articleId;

    private Long categoryId;
}