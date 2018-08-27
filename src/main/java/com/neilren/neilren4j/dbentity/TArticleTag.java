package com.neilren.neilren4j.dbentity;

import lombok.Data;

@Data
public class TArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;
}