package com.neilren.neilren4j.entity;

import lombok.Data;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName AlicmapiIP
 * @Description TODO
 * @Date 2018/8/20 10:49
 */
@Data
public class AlicmapiIP {
    private int ret;
    private String msg;
    private String log_id;
    private AlicmapiIPData data;
}
