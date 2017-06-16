package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.FrielinkService;
import com.neilren.neilren4j.modules.article.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        mv.addObject("frieLinkList", frielinkService.getAllFrieLink());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("Index", Index);
        mv.setViewName("home/index");
        return mv;
    }
}
