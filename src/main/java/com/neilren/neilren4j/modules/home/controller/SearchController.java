package com.neilren.neilren4j.modules.home.controller;

import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.SearchService;
import com.neilren.neilren4j.modules.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by neil on 14/06/2017.
 */
@Controller
public class SearchController extends BaseController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/Search/")
    public ModelAndView SearchArticle(@RequestParam("wd") String wd, @RequestParam(value = "page", required = false) String page) {
        ModelAndView mv = new ModelAndView();
        int intPage = 1;
        try {
            if (page == null)
                intPage = 1;
            else
                intPage = Integer.parseInt(page);
            if (intPage > 0) {
                mv.addObject("articleWithBLOBsList", searchService.Search(wd, intPage));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        mv.addObject("articlePagingList", searchService.getSearchPagingList(wd, intPage));
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("archivesList", articleService.getArticleArchives());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("articleTop10ByViewsList", articleService.getArticleTop10ByViewsList());
        mv.addObject("wd", wd);
        mv.addObject("Index", intPage);
        mv.setViewName("search/page");
        return mv;
    }
}
