package com.neilren.neilren4j.dbentity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TLogAccess implements Serializable {
    private static final long serialVersionUID = -7898194272883238670L;
    private Long id;

    private Date datetime;

    private String ip;

    private String protocol;

    private String scheme;

    private String hostName;

    private String port;

    private String method;

    private String sessionId;

    private String accept;

    private String acceptLanguage;

    private String browserType;

    private String browserName;

    private String browserManufacture;

    private String browserGroup;

    private String browserEngine;

    private String browserMajorVersion;

    private String browserMinorVersion;

    private String browserVersion;

    private String osName;

    private String osType;

    private String osGroup;

    private String osManufacturer;

    private String area;

    private String country;

    private String city;

    private String region;

    private String isp;

    private String referer;

    private String url;

    private String query;

    private String userAgent;

    private String cookie;
}