package com.neilren.neilren4j.entity;

import com.neilren.neilren4j.dbentity.TMenu;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Menu
 * @Description TODO
 * @Date 2018/7/18 14:43
 */
public class Menu extends TMenu {
    private boolean activity;

    public Menu() {
        this.activity = false;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }
}
