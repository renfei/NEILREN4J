package com.neilren.neilren4j.modules.article.controller;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.controller.BaseController;

import com.neilren.neilren4j.common.entity.JsonObject;
import com.neilren.neilren4j.common.utils.RequestUtils;
import com.neilren.neilren4j.modules.article.entity.Article;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.entity.Comment;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.CommentService;
import com.neilren.neilren4j.modules.article.service.TagService;
import com.neilren.neilren4j.modules.home.controller.ErrorController;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private ErrorController errorController;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RequestUtils requestUtils;

    /**
     * 根据文章ID获取文章
     *
     * @param ID 文章ID
     * @return
     */
    @RequestMapping("/Article/{ID}")
    public ModelAndView getArticleById(@PathVariable String ID, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        ArticleWithBLOBs articleWithBLOBs = articleService.findById(ID);

        if (articleWithBLOBs == null)
            return errorController.error404(response);
        mv.addObject("articleWithBLOBs", articleWithBLOBs);
        mv.addObject("tagList", tagService.getAllTag());
        mv.addObject("archivesList", articleService.getArticleArchives());
        mv.addObject("articleTop10ByDateList", articleService.getArticleTop10ByDateList());
        mv.addObject("articleTop10ByViewsList", articleService.getArticleTop10ByViewsList());
        if (Global.getConfig("neilren.comment").equals("true")) {
            mv.addObject("articleCommentList", commentService.getCommentByArticleID(ID));
        }
        mv.addObject("comment", Global.getConfig("neilren.comment"));
        mv.addObject("advert", Global.getConfig("neilren.advert"));
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

    @ResponseBody
    @RequestMapping(value = "/Article/Comment", method = RequestMethod.POST)
    public JsonObject setArticleComment(@ModelAttribute Comment comment, HttpServletRequest request) {
        comment.setAuthor_IP(requestUtils.getIpAddr(request));
        comment.setAgent(request.getHeader("User-Agent"));
        return commentService.insterComment(comment);
    }
}
