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
 * @ClassName TagController
 * @Description TODO
 * @Date 2018/7/19 22:11
 */
@Controller
@RequestMapping("/tag")
public class TagController extends BasePageController {
    @RequestMapping({"{en_name}", "{en_name}/"})
    public ModelAndView getArticleByTag(@PathVariable("en_name") String en_name, HttpServletRequest request) throws NoHandlerFoundException {
        ModelAndView mv = new ModelAndView();
        TTag tag = articleService.getTagByEnName(en_name.trim().toLowerCase());
        if (tag == null) {
            HttpHeaders headers = new HttpHeaders();
            throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
        } else {
            List<Menu> menuList = siteMenuService.getAllMenu("/blog");
            mv.addObject("menu", menuList);
            mv.addObject("type", "tag");
            mv.addObject("value", en_name.trim().toLowerCase());
            HeadTitle headTitle = new HeadTitle(tag.getZhName() + " - Blog - " + siteName, tag.getDescribe(), tag.getEnName());
            mv.addObject("title", headTitle);
            mv.addObject("eyebrow", "Blog - Tag");
            mv.addObject("h1", tag.getZhName());
            mv.addObject("describe", tag.getDescribe());
            articleService.setCloudTag(mv);
            //获取10篇文章
            List<Article> articles = articleService.selectArticleListByTag(tag, 1);
            mv.addObject("articles", articles);
            mv.addObject("submenu", articleService.getSubMenu(null));
            mv.setViewName("article/list");
        }
        return mv;
    }
}
