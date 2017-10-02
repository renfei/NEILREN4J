package com.neilren.neilren4j.modules.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by neil on 09/08/2017.
 */
@Controller
@RequestMapping(value = "/tools")
public class ToolsController {
    @RequestMapping(value = "bjyikatong")
    public ModelAndView BMACCardRecord(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("tools/bjyikatong");
        return mv;
    }
}
