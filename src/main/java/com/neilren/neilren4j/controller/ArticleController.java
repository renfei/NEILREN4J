package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.entity.BaiduXiongZhangJsonLD;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ArticleController
 * @Description TODO
 * @Date 2018/7/19 12:56
 */
@Slf4j
@Controller
@RequestMapping("/Article")
public class ArticleController extends BasePageController {
    @RequestMapping({"{id}", "{id}/"})
    public ModelAndView getArticleById(@PathVariable("id") Long id, HttpServletRequest request) throws NoHandlerFoundException {
        ModelAndView mv = new ModelAndView();
        List<Menu> menuList = siteMenuService.getAllMenu("/blog");
        mv.addObject("menu", menuList);
        try {
            Article article = articleService.getArticleById(id);
            if (article == null) {
                //查询不到文章
                HttpHeaders headers = new HttpHeaders();
                throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
            } else {
                HeadTitle headTitle = new HeadTitle(article.getTitle() + " - " + siteName, article.getDescribes(), article.getKeyword());
                mv.addObject("title", headTitle);
                mv.addObject("article", article);
                //其他文章
                List<Article> articleOther = articleService.getOtherRelated(article);
                if (articleOther != null && articleOther.size() > 0) {
                    mv.addObject("article_other", articleOther);
                }
                //TopView
                List<Article> articleTopView = articleService.selectTopViewArticle(1, 4);
                if (articleTopView != null && articleTopView.size() > 0) {
                    mv.addObject("topview", articleTopView);
                }
                //百度熊掌号搜索结果出图
                BaiduXiongZhangJsonLD baiduXiongZhangJsonLD = new BaiduXiongZhangJsonLD(
                        neilren4jConfig.getBaidu().getSite() + "Article/" + article.getId(),
                        article.getTitle(),
                        new ArrayList<String>() {{
                            if (article.getImg() != null && article.getImg() != "") {
                                add(article.getImg());
                            }
                        }},
                        article.getDate(),
                        "1233456"
                );
                mv.addObject("baiduXiongZhangJsonLD", baiduXiongZhangJsonLD.getHtmlCode(baiduXiongZhangJsonLD));
                mv.setViewName("article/detail");
            }
        } catch (NoHandlerFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return mv;
    }
}
