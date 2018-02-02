package com.neilren.neilren4j.modules.console.controller;

import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.security.Captcha;
import com.neilren.neilren4j.modules.console.service.ConsoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/console")
public class ConsoleController {
    private static Logger log = Logger.getLogger(ConsoleController.class);

    @Autowired
    private ConsoleService consoleService;

    /**
     * 控制台首页
     *
     * @return
     */
    @RequestMapping("")
    public ModelAndView getConsole() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("console/index");
        return mv;
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("console/login");
        return mv;
    }

    /**
     * 注销登录
     *
     * @return
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("logined");
        return new ModelAndView("redirect:/console/login");
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Captcha.getCaptcha(request, response);
    }

    /**
     * 登陆业务
     *
     * @param otp
     * @param gac
     * @param ver
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject login(HttpServletRequest request, String otp, String gac, String ver) {
        return consoleService.login(request, otp, gac, ver);
    }

}
