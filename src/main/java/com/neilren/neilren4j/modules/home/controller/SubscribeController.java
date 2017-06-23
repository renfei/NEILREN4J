package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by neil on 23/06/2017.
 */
@Controller
@RequestMapping(value = "/Subscribe")
public class SubscribeController extends BaseController {
    @RequestMapping(value = "")
    public ModelAndView getDefault() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subscribe/index");
        return mv;
    }
}
