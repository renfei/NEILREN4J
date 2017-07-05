package com.neilren.neilren4j.modules.api.entity;

import com.neilren.neilren4j.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by neil on 03/07/2017.
 */
public class WeChatMsg extends DataEntity<WeChatMsg> {
    private Long id;
    private Date recording_time;
    private Integer type;
    private String from_user_name;
    private Long create_time;
    private String msg_type;
    private String content;
    private Long msg_id;
    private String original;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecording_time() {
        return recording_time;
    }

    public void setRecording_time(Date recording_time) {
        this.recording_time = recording_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrom_user_name() {
        return from_user_name;
    }

    public void setFrom_user_name(String from_user_name) {
        this.from_user_name = from_user_name;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
