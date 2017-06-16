package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by neil on 12/06/2017.
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController extends BaseController {
    @RequestMapping(value = "404")
    public void error404() {
        System.out.println("404!");
    }

    @RequestMapping(value = "500")
    public ModelAndView error500() {
        System.out.println("500!");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/500");
        return mv;
    }
}
