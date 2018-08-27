package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.util.Date;

@Data
public class TUserRole {
    private Long id;

    private Long userId;

    private Long roleId;

    private Date expiryTime;
}