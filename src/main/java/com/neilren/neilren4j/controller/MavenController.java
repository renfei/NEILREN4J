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
 * @ClassName MavenController
 * @Description TODO
 * @Date 2018/7/20 09:59
 */
@Controller
@RequestMapping("/maven")
public class MavenController extends BasePageController {
    @RequestMapping("")
    public ModelAndView getMavenPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("maven");
        List<Menu> menuList = siteMenuService.getAllMenu("/maven");
        mv.addObject("menu", menuList);
        HeadTitle headTitle = new HeadTitle("Maven Repository Mirror - " + siteName,
                "Maven 仓库镜像服务。由于中国大陆访问境外服务较为缓慢，为使广大开发者快速便捷的使用 Maven 服务，我们提供了 Maven 中央仓库的镜像服务。同时支持 Maven / NuGet。",
                "Maven, 国内, 境内, 镜像, 代理, 服务");
        mv.addObject("title", headTitle);
        return mv;
    }
}
