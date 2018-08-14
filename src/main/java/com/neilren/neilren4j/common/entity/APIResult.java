package com.neilren.neilren4j.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName APIResult
 * @Description TODO
 * @Date 2018/7/21 12:29
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class APIResult<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 消息信息
     */
    private String message;
    /**
     * 响应返回的数据对象
     */
    private T data;
    /**
     * 错误码（后期建立错误代码Wiki库）
     */
    private String errorcode;
    /**
     * 接口当前版本号
     */
    private String version;
    /**
     * 响应时间戳
     */
    private Long datetime;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime.getTime();
    }
}