package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.util.Date;

@Data
public class TUser {
    private Long id;

    private String account;

    private String passwd;

    private String email;

    private String salt;

    private Date birthday;

    private String sex;

    private String secret;

    private Date joinDate;

    private Date lastLoginDate;

    private Integer userState;
}