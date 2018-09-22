package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TUser implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
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