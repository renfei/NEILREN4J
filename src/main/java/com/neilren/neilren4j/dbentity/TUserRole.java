package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TUserRole implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private Long id;

    private Long userId;

    private Long roleId;

    private Date expiryTime;
}