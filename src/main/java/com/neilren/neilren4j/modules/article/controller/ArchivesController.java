package com.neilren.neilren4j.modules.article.controller;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.ArchivesService;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by neil on 19/06/2017.
 */
@Controller
public class ArchivesController extends BaseController {

    @Autowired
    ArchivesService archivesService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/Archives/{date}")
    public ModelAndView getArticleByTag(@PathVariable String date) {
        return getArticleByTag(date, "1");
    }

    @RequestMapping("/Archives/{date}/page/{index}")
    public ModelAndView getArticleByTag(@PathVariable String date, @PathVariable String index) {
        ModelAndView mv = new ModelAndView();
        int Index = 0;
        try {
            Index = Integer.parseInt(index);
        } catch (Exception e) {
        }
        if (Index > 0) {
            List<ArticleWithBLOBs> articleWithBLOBsList = archivesService.getArticleList(date, Index);
            mv.addObject("articleWithBLOBsList", articleWithBLOBsList);
        }
        mv.addObject("articlePagingList", archivesService.getArticlePagingList(date, Index));
        mv.addObject("articlePagingUrl", "/Archives/" + date + "/page/");
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("archivesList", articleService.getArticleArchives());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("Index", Index);
        mv.addObject("advert", Global.getConfig("neilren.advert"));
        mv.setViewName("home/index");
        return mv;
    }
}
