package com.neilren.neilren4j.modules.api.entity;

import java.io.Serializable;

/**
 * Created by neil on 10/07/2017.
 */
public class PhilisenseKaoQins implements Serializable {
    private String userName;
    private String zA0101;
    private String sJ0106;
    private String state;
    private String minSJ0100;
    private String maxSH0100;
    private String superimposed;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getzA0101() {
        return zA0101;
    }

    public void setzA0101(String zA0101) {
        this.zA0101 = zA0101;
    }

    public String getsJ0106() {
        return sJ0106;
    }

    public void setsJ0106(String sJ0106) {
        this.sJ0106 = sJ0106;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMinSJ0100() {
        return minSJ0100;
    }

    public void setMinSJ0100(String minSJ0100) {
        this.minSJ0100 = minSJ0100;
    }

    public String getMaxSH0100() {
        return maxSH0100;
    }

    public void setMaxSH0100(String maxSH0100) {
        this.maxSH0100 = maxSH0100;
    }

    public String getSuperimposed() {
        return superimposed;
    }

    public void setSuperimposed(String superimposed) {
        this.superimposed = superimposed;
    }
}
