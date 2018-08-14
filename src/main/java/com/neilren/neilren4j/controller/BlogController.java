package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.dbentity.TCategory;
import com.neilren.neilren4j.dbentity.TTag;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.entity.ArticleAPI;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BlogController
 * @Description TODO
 * @Date 2018/7/21 09:43
 */
@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogController extends BasePageController {
    @RequestMapping("")
    public ModelAndView getArticleList() {
        ModelAndView mv = new ModelAndView();
        List<Menu> menuList = siteMenuService.getAllMenu("/blog");
        mv.addObject("menu", menuList);
        mv.addObject("type", "blog");
        HeadTitle headTitle = new HeadTitle("Blog - " + siteName, "分享即提高，通过分享，收获最大的是你自己。", null);
        mv.addObject("title", headTitle);
        mv.addObject("eyebrow", "Blog");
        mv.addObject("h1", "博客");
        mv.addObject("describe", "分享即提高，通过分享，收获最大的是你自己。");
        articleService.setCloudTag(mv);
        //获取10篇文章
        List<Article> articles = articleService.selectAllArticle();
        mv.addObject("articles", articles);
        mv.addObject("submenu", articleService.getSubMenu(null));
        mv.setViewName("article/list");
        return mv;
    }

    @RequestMapping(value = "paging", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<List<ArticleAPI>> Paging(int page, String type, String value) {
        APIResult<List<ArticleAPI>> apiResult = new APIResult<>();
        List<Article> articles = new ArrayList<>();
        List<ArticleAPI> articleAPIS = new ArrayList<>();
        try {
            switch (type.trim().toLowerCase()) {
                case "blog":
                    articles = articleService.selectAllArticle(page);
                    break;
                case "cat":
                    TCategory cat = articleService.getCatByEnName(value.trim().toLowerCase());
                    if (cat != null) {
                        articles = articleService.selectArticleListByCat(cat, page);
                    }
                    break;
                case "tag":
                    TTag tag = articleService.getTagByEnName(value.trim().toLowerCase());
                    if (tag != null) {
                        articles = articleService.selectArticleListByTag(tag, page);
                    }
                    break;
            }
            for (Article a : articles) {
                ArticleAPI articleAPI = new ArticleAPI(a);
                articleAPIS.add(articleAPI);
            }
            apiResult.setSuccess(true);
            apiResult.setData(articleAPIS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            apiResult.setSuccess(false);
            apiResult.setMessage(e.getMessage());
        }
        return apiResult;
    }
}
