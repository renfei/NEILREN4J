package com.neilren.neilren4j.common.entity;

/**
 * 通用Json对象
 */
public class JsonObject {
    private int state;
    private String message;
    private Object object;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
