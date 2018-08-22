package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName AboutService
 * @Description TODO
 * @Date 2018/8/21 21:12
 */
@Service
public class AboutService extends BasePageService {
    public List<Menu> getSubMenu(String url) {
        List<Menu> subMenu = new ArrayList<>();
        Menu menu = new Menu();
        menu.setUrl("/about");
        menu.setActivity(false);
        menu.setName("About");
        subMenu.add(menu);
        Menu menu2 = new Menu();
        menu2.setUrl("/about/chart");
        menu2.setActivity(false);
        menu2.setName("OpenDate");
        subMenu.add(menu2);
        for (Menu m : subMenu) {
            if (m.getUrl().equals(url.trim().toLowerCase())) {
                m.setActivity(true);
            }
        }
        return subMenu;
    }
}
