package com.neilren.neilren4j.common.entity;

import java.io.Serializable;

public class IPDBObject implements Serializable {
    private String StartIP;
    private String EndIP;
    private String Country;
    private String Local;
    private String Version;

    public String getStartIP() {
        return StartIP;
    }

    public void setStartIP(String startIP) {
        StartIP = startIP;
    }

    public String getEndIP() {
        return EndIP;
    }

    public void setEndIP(String endIP) {
        EndIP = endIP;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
