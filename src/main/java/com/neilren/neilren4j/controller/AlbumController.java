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
 * @ClassName AlbumController
 * @Description TODO
 * @Date 2018/8/2 21:32
 */
@Controller
@RequestMapping("/album")
public class AlbumController extends BasePageController {
    @RequestMapping("")
    public ModelAndView getAboutPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("album/index");
        List<Menu> menuList = siteMenuService.getAllMenu("/album");
        mv.addObject("menu", menuList);
        HeadTitle headTitle = new HeadTitle("数码相册 - " + siteName, null, null);
        mv.addObject("title", headTitle);
        return mv;
    }
}
