package com.neilren.neilren4j.modules.article.controller;

import com.neilren.neilren4j.common.controller.BaseController;

import com.neilren.neilren4j.modules.article.entity.Article;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by neil on 07/06/2017.
 */
@Controller
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    /**
     * 根据文章ID获取文章
     *
     * @param ID 文章ID
     * @return
     */
    @RequestMapping("/Article/{ID}")
    public ModelAndView getArticleById(@PathVariable String ID) {
        ModelAndView mv = new ModelAndView();
        ArticleWithBLOBs articleWithBLOBs = articleService.findById(ID);
        mv.addObject("articleWithBLOBs", articleWithBLOBs);
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("archivesList", articleService.getArticleArchives());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("articleTop10ByViewsList", articleService.getArticleTop10ByViewsList());
        mv.setViewName("article/articlepage");
        return mv;
    }

    /**
     * 给文章评分
     *
     * @param longId 文章ID
     * @param grade  评分等级
     */
    @ResponseBody
    @RequestMapping(value = "/Article/Grade", method = RequestMethod.POST)
    public void setArticleGrade(String longId, String grade) {
        articleService.setArticleGrade(longId, grade);
    }
}
