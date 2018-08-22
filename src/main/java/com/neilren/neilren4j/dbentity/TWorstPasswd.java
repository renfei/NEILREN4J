package com.neilren.neilren4j.dbentity;

public class TWorstPasswd {
    private Long id;

    private String passwd;

    private String md5;

    private String sha1;

    private String sha224;

    private String sha256;

    private String sha384;

    private String sha512;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha224() {
        return sha224;
    }

    public void setSha224(String sha224) {
        this.sha224 = sha224;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getSha384() {
        return sha384;
    }

    public void setSha384(String sha384) {
        this.sha384 = sha384;
    }

    public String getSha512() {
        return sha512;
    }

    public void setSha512(String sha512) {
        this.sha512 = sha512;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}