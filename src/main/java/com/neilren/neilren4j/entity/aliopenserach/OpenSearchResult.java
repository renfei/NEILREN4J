package com.neilren.neilren4j.entity.aliopenserach;

import lombok.Data;

import java.io.Serializable;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName OpenSearchResult
 * @Description TODO
 * @Date 2018/7/27 16:16
 */
@Data
public class OpenSearchResult  implements Serializable {
    private String status;
    private String request_id;
    private Results result;
    private Object errors;
    private String tracer;
}