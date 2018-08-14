package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.dbentity.TCategory;
import com.neilren.neilren4j.dbentity.TTag;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.Menu;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName CategoryController
 * @Description TODO
 * @Date 2018/7/19 22:10
 */
@Controller
@RequestMapping("/cat")
public class CategoryController extends BasePageController {
    @RequestMapping({"{en_name}", "{en_name}/"})
    public ModelAndView getArticleByTag(@PathVariable("en_name") String en_name, HttpServletRequest request) throws NoHandlerFoundException {
        ModelAndView mv = new ModelAndView();
        TCategory cat = articleService.getCatByEnName(en_name.trim().toLowerCase());
        if (cat == null) {
            HttpHeaders headers = new HttpHeaders();
            throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
        } else {
            List<Menu> menuList = siteMenuService.getAllMenu("/blog");
            mv.addObject("menu", menuList);
            mv.addObject("type", "cat");
            mv.addObject("value", en_name.trim().toLowerCase());
            HeadTitle headTitle = new HeadTitle(cat.getZhName() + " - Blog - " + siteName, cat.getDescribe(), cat.getEnName());
            mv.addObject("title", headTitle);
            mv.addObject("eyebrow", "Blog - Category");
            mv.addObject("h1", cat.getZhName());
            mv.addObject("describe", cat.getDescribe());
            articleService.setCloudTag(mv);
            //获取10篇文章
            List<Article> articles = articleService.selectArticleListByCat(cat, 1);
            mv.addObject("articles", articles);
            mv.addObject("submenu", articleService.getSubMenu(en_name));
            mv.setViewName("article/list");
        }
        return mv;
    }
}
