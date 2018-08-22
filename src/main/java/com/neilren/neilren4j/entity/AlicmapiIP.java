package com.neilren.neilren4j.entity;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName AlicmapiIP
 * @Description TODO
 * @Date 2018/8/20 10:49
 */
public class AlicmapiIP {
    private int ret;
    private String msg;
    private String log_id;
    private AlicmapiIPData data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public AlicmapiIPData getData() {
        return data;
    }

    public void setData(AlicmapiIPData data) {
        this.data = data;
    }
}
