package com.neilren.neilren4j.entity;

import com.neilren.neilren4j.dbentity.TArticle;
import lombok.Data;

import java.io.Serializable;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Article
 * @Description TODO
 * @Date 2018/7/21 10:58
 */
@Data
public class Article extends TArticle implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private String enName;
    private String zh_name;
}
