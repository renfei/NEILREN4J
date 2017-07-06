package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.common.entity.Changefreq;
import com.neilren.neilren4j.common.entity.SiteMapXml;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.FrielinkService;
import com.neilren.neilren4j.modules.article.service.TagService;

import com.neilren.neilren4j.modules.home.service.SiteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页Controller
 */
@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FrielinkService frielinkService;
    @Autowired
    private SiteMapService siteMapService;

    @RequestMapping(value = "/")
    public ModelAndView homePage() {
        return homePage("1");
    }

    @RequestMapping(value = "/page/{index}")
    public ModelAndView homePage(@PathVariable String index) {
        ModelAndView mv = new ModelAndView();
        int Index = 0;
        try {
            Index = Integer.parseInt(index);
        } catch (Exception e) {
        }
        if (Index > 0) {
            List<ArticleWithBLOBs> articleWithBLOBsList = articleService.getArticleList(Index);
            mv.addObject("articleWithBLOBsList", articleWithBLOBsList);
        }
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("archivesList", articleService.getArticleArchives());
        mv.addObject("articlePagingList", articleService.getArticlePagingList(Index));
        mv.addObject("articlePagingUrl", "/page/");
        mv.addObject("frieLinkList", frielinkService.getAllFrieLink());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("articleTop10ByViewsList", articleService.getArticleTop10ByViewsList());
        mv.addObject("Index", Index);
        mv.addObject("advert", Global.getConfig("neilren.advert"));
        mv.setViewName("home/index");
        return mv;
    }

    @RequestMapping(value = "/robots.txt")
    @ResponseBody
    public ModelAndView getRobotsTxt(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/robotstxt");
        response.setContentType("text/plain;charset=UTF-8");
        return mv;
    }

    @RequestMapping(value = "/sitemap.xml")
    @ResponseBody
    public ModelAndView getSiteMapXml(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<SiteMapXml> siteMapXmls = siteMapService.getSiteMaps();
        mv.addObject("siteMapXmls", siteMapXmls);
        mv.setViewName("home/sitemapxml");
        response.setContentType("text/xml;charset=UTF-8");
        return mv;
    }

    @RequestMapping(value = "/sitemap.xsl")
    @ResponseBody
    public ModelAndView getSiteMapXsl(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        response.setContentType("application/octet-stream");
        mv.setViewName("home/sitemapxsl");
        return mv;
    }
}
