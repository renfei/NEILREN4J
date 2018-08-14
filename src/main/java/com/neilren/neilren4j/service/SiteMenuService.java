package com.neilren.neilren4j.service;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.dbentity.TMenu;
import com.neilren.neilren4j.entity.Menu;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SiteMenuService
 * @Description TODO
 * @Date 2018/7/18 14:39
 */
@Service
public class SiteMenuService extends BasePageService {
    public List<Menu> getAllMenu() {
        return getAllMenu(null);
    }

    public List<Menu> getAllMenu(String urlPath) {
        List<TMenu> tMenuList = menuMapper.selectAll();
        String json = JSON.toJSONString(tMenuList);
        List<Menu> menuList = JSON.parseArray(json, Menu.class);
        for (int i = 0; i < menuList.size(); i++) {
            if (urlPath == null && i == 0) {
                Menu menu = menuList.get(i);
                menu.setActivity(true);
                menuList.set(i, menu);
                break;
            } else {
                Menu menu = menuList.get(i);
                if (menu.getUrl().toLowerCase().trim().equals(urlPath.toLowerCase().trim())) {
                    menu.setActivity(true);
                    menuList.set(i, menu);
                    break;
                }
            }
        }
        return menuList;
    }
}
