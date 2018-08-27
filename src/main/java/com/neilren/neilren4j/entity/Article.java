package com.neilren.neilren4j.entity;

import com.neilren.neilren4j.dbentity.TArticle;
import lombok.Data;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Article
 * @Description TODO
 * @Date 2018/7/21 10:58
 */
@Data
public class Article extends TArticle {
    private String enName;
    private String zh_name;
}
