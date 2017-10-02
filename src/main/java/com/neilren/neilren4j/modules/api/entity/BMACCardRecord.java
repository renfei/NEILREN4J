package com.neilren.neilren4j.modules.api.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neil on 09/08/2017.
 */
public class BMACCardRecord implements Comparable,Serializable {
    private int type;
    private String date;
    private String scene;
    private String  money;
    private String  balance;
    private String  cumulative;
    private String  before;
    private String  after;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCumulative() {
        return cumulative;
    }

    public void setCumulative(String cumulative) {
        this.cumulative = cumulative;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    @Override
    public int compareTo(Object o) {
        BMACCardRecord obj =(BMACCardRecord)o;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{

            Date dt1 = df.parse(this.date);
            Date dt2 = df.parse(obj.date);
            if (dt1.getTime() < dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() > dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        }catch (Exception ex){
            return 0;
        }
    }
}
