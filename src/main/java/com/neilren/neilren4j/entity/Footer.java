package com.neilren.neilren4j.entity;

import lombok.Data;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Footer
 * @Description TODO
 * @Date 2018/7/18 16:40
 */
@Data
public class Footer {
    private List<Link> linkList;
    private FootMenu footMenu;
    private String processed;
    private String calls;

    @Data
    class FootMenu {

    }
}
