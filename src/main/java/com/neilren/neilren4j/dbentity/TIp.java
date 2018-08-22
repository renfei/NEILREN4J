package com.neilren.neilren4j.dbentity;

public class TIp {
    private Long startip;

    private Long endip;

    private String country;

    private String local;

    public Long getStartip() {
        return startip;
    }

    public void setStartip(Long startip) {
        this.startip = startip;
    }

    public Long getEndip() {
        return endip;
    }

    public void setEndip(Long endip) {
        this.endip = endip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}