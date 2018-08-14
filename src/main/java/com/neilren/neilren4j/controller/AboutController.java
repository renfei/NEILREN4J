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
 * @ClassName AboutController
 * @Description TODO
 * @Date 2018/7/19 22:15
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BasePageController {
    @RequestMapping("")
    public ModelAndView getAboutPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about");
        List<Menu> menuList = siteMenuService.getAllMenu("/about");
        mv.addObject("menu", menuList);
        HeadTitle headTitle = new HeadTitle("关于 - " + siteName, null, null);
        mv.addObject("title", headTitle);
        return mv;
    }
}
