package com.neilren.neilren4j.entity.aliopenserach;

import java.io.Serializable;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName OpenSearchResult
 * @Description TODO
 * @Date 2018/7/27 16:16
 */
public class OpenSearchResult  implements Serializable {
    private String status;
    private String request_id;
    private Results result;
    private Object errors;
    private String tracer;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Results getResult() {
        return result;
    }

    public void setResult(Results result) {
        this.result = result;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public String getTracer() {
        return tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }
}