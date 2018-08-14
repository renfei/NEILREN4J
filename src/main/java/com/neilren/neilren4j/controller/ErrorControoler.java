package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.entity.HeadTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ErrorControoler
 * @Description TODO
 * @Date 2018/7/27 11:30
 */
@Controller
@RequestMapping("/error")
public class ErrorControoler extends BasePageController {
    /**
     * 401页面
     */
    @GetMapping(value = "401")
    public String error_401() {
        return "error/401";
    }

    /**
     * 401页面
     */
    @GetMapping(value = "403")
    public ModelAndView error_403() {
        ModelAndView mv = new ModelAndView();
        HeadTitle headTitle = new HeadTitle("403 - Forbidden - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("error/403");
        return mv;
    }

    /**
     * 404页面
     */
    @GetMapping(value = "404")
    public ModelAndView error_404() {
        ModelAndView mv = new ModelAndView();
        HeadTitle headTitle = new HeadTitle("404 - Not Found - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("error/404");
        return mv;
    }

    /**
     * 404页面
     */
    @GetMapping(value = "405")
    public ModelAndView error_405() {
        ModelAndView mv = new ModelAndView();
        HeadTitle headTitle = new HeadTitle("405 - Method Not Allowed - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("error/405");
        return mv;
    }

    /**
     * 500页面
     */
    @GetMapping(value = "500")
    public ModelAndView error_500() {
        ModelAndView mv = new ModelAndView();
        HeadTitle headTitle = new HeadTitle("500 - Internal Server Error - " + siteName, null, null);
        mv.addObject("title", headTitle);
        mv.setViewName("error/500");
        return mv;
    }
}
