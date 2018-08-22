package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName KitBoxController
 * @Description TODO
 * @Date 2018/8/2 21:00
 */
@Controller
@RequestMapping("/kitbox")
public class KitBoxController extends BasePageController {
    @RequestMapping("")
    public ModelAndView getKitBox() {
        ModelAndView mv = new ModelAndView();
        List<Menu> menuList = siteMenuService.getAllMenu("/kitbox");
        mv.addObject("menu", menuList);
        HeadTitle headTitle = new HeadTitle("开发工具箱 - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("kitbox/index");
        return mv;
    }
}
