package com.neilren.neilren4j.controller.system;

import com.neilren.neilren4j.common.baseclass.BaseSysController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SysController
 * @Description TODO
 * @Date 2018/7/31 17:09
 */
@Controller
@RequestMapping("/system")
//@RequiresRoles("admin")
public class SysController extends BaseSysController {

    @RequestMapping("console")
    public ModelAndView getConsole() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/console");
        return mv;
    }
}
