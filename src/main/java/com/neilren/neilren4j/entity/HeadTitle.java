package com.neilren.neilren4j.entity;

import lombok.Data;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName HeadTitle
 * @Description TODO
 * @Date 2018/8/14 17:16
 */
@Data
public class HeadTitle {
    private String title;
    private String description;
    private String keyword;
    private String canonical;
    private String miphtml;

    public HeadTitle(String title, String description, String keyword) {
        this.title = title;
        this.description = description;
        this.keyword = keyword;
    }
}
