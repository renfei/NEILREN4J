package com.neilren.neilren4j.modules.article.controller;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.controller.BaseController;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
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
public class TagController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/tag/{en_name}")
    public ModelAndView getArticleByTag(@PathVariable String en_name) {
        return getArticleByTag(en_name, "1");
    }

    @RequestMapping("/tag/{en_name}/page/{index}")
    public ModelAndView getArticleByTag(@PathVariable String en_name, @PathVariable String index) {
        ModelAndView mv = new ModelAndView();
        int Index = 0;
        try {
            Index = Integer.parseInt(index);
        } catch (Exception e) {
        }
        if (Index > 0) {
            List<ArticleWithBLOBs> articleWithBLOBsList = tagService.getArticleList(en_name, Index);
            mv.addObject("articleWithBLOBsList", articleWithBLOBsList);
        }
        mv.addObject("articlePagingList", tagService.getArticlePagingList(en_name, Index));
        mv.addObject("articlePagingUrl", "/tag/" + en_name + "/page/");
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("archivesList", articleService.getArticleArchives());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("advert", Global.getConfig("neilren.advert"));
        mv.addObject("Index", Index);
        mv.setViewName("home/index");
        return mv;
    }
}
