package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by neil on 20/06/2017.
 */
@Controller
public class ErrorController extends BaseController {
    @RequestMapping(value = "*")
    public ModelAndView error404(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/404");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return mv;
    }
}
