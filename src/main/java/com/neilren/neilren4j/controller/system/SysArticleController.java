package com.neilren.neilren4j.controller.system;

import com.neilren.neilren4j.common.baseclass.BaseSysController;
import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.common.entity.EasyuiDatagrid;
import com.neilren.neilren4j.dbentity.TArticleCategory;
import com.neilren.neilren4j.dbentity.TArticleTag;
import com.neilren.neilren4j.dbentity.TCategory;
import com.neilren.neilren4j.dbentity.TTag;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.service.ArticleService;
import com.neilren.neilren4j.service.BaiduPushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ArticleController
 * @Description TODO
 * @Date 2018/8/5 14:33
 */
@Controller
@Slf4j
@RequestMapping("/system/article")
@RequiresRoles("admin")
public class SysArticleController extends BaseSysController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private BaiduPushService baiduPushService;

    @RequestMapping("list")
    public ModelAndView getList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/article/list");
        return mv;
    }

    @RequestMapping("getDateList")
    @ResponseBody
    public EasyuiDatagrid getDateList(int page, int rows) {
        EasyuiDatagrid easyuiDatagrid = new EasyuiDatagrid();
        articleService.selectAllArticle(easyuiDatagrid, page, rows);
        return easyuiDatagrid;
    }

    @RequestMapping(value = "saveArticle", method = RequestMethod.POST)
    @ResponseBody
    public APIResult saveArticle(Article article, Long cat, String tag) {
        APIResult result = new APIResult();
        if (article != null) {
            article.setDate(new Date(System.currentTimeMillis()));
            articleService.saveArticle(article);
            TArticleCategory articleCategory = new TArticleCategory();
            articleCategory.setArticleId(article.getId());
            articleCategory.setCategoryId(cat);
            articleService.saveArticleCategory(articleCategory);
            String[] tags = tag.split(",");
            for (String t : tags) {
                TArticleTag articleTag = new TArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(Long.valueOf(t));
                articleService.saveArticleTag(articleTag);
            }
            result.setSuccess(true);
            try {
                baiduPushService.pushBaidu(new String[]{neilren4jConfig.getBaidu().getSite() + "/Article/" + article.getId()},
                        article.getSource() == 0 ? true : false);
                baiduPushService.pushXingzhang(new String[]{neilren4jConfig.getBaidu().getSite() + "/Article/" + article.getId()});
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return result;
            }
        }
        return result;
    }

    @RequestMapping("getCategorys")
    @ResponseBody
    public List<TCategory> getCategorys() {
        return articleService.getAllCategory();
    }

    @RequestMapping("getTags")
    @ResponseBody
    public List<TTag> getTags() {
        return articleService.getAllTag();
    }
}
