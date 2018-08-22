package com.neilren.neilren4j.controller.about;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.Menu;
import com.neilren.neilren4j.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ChartController
 * @Description TODO
 * @Date 2018/8/20 17:48
 */
@Controller
@RequestMapping("/about/chart")
public class ChartController extends BasePageController {
    @Autowired
    private AboutService aboutService;

    @RequestMapping("")
    public ModelAndView getChart() {
        ModelAndView mv = new ModelAndView();
        List<Menu> menuList = siteMenuService.getAllMenu("/about");
        mv.addObject("menu", menuList);
        mv.addObject("submenu", aboutService.getSubMenu("/about/chart"));
        HeadTitle headTitle = new HeadTitle("开放数据 - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("chart");
        return mv;
    }
}
