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
 * @ClassName LegalController
 * @Description TODO
 * @Date 2018/8/2 18:18
 */
@Controller
@RequestMapping("/legal")
public class LegalController extends BasePageController {
    @RequestMapping("privacystatement")
    public ModelAndView getPrivacystatement() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("legal/privacystatement");
        HeadTitle headTitle = new HeadTitle("隐私声明和 Cookie - " + siteName, "请阅读本隐私声明中特定于服务的详细信息，这些详细信息提供了其他相关信息。 此声明适用于 NEILREN.COM 与你之间的互动、 NEILREN.COM 提供的所有服务。", "隐私, 声明, Cookie");
        mv.addObject("title", headTitle);
        List<Menu> menuList = siteMenuService.getAllMenu("/about");
        mv.addObject("menu", menuList);
        return mv;
    }

    @RequestMapping("intellectualproperty")
    public ModelAndView getIntellectualproperty() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("legal/intellectualproperty");
        HeadTitle headTitle = new HeadTitle("使用条款 - " + siteName, "NEILREN.COM为您提供的服务须受以下使用条款（“TOU”）的约束。NEILREN.COM保留随时更新 TOU 的权利，恕不另行通知。单击网页底部的“使用条款”超文本链接，可查看最新版本的 TOU。", "使用条款");
        mv.addObject("title", headTitle);
        List<Menu> menuList = siteMenuService.getAllMenu("/about");
        mv.addObject("menu", menuList);
        return mv;
    }
}
