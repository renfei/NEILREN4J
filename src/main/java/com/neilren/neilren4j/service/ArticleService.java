package com.neilren.neilren4j.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.common.entity.EasyuiDatagrid;
import com.neilren.neilren4j.common.util.DelTagsUtil;
import com.neilren.neilren4j.dao.TArticleCategoryMapper;
import com.neilren.neilren4j.dao.TArticleTagMapper;
import com.neilren.neilren4j.dbentity.*;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ArticleService
 * @Description TODO
 * @Date 2018/7/21 09:44
 */
@Service
public class ArticleService extends BasePageService {
    @Autowired
    private DelTagsUtil delTagsUtil;
    @Autowired
    private TArticleCategoryMapper articleCategoryMapper;
    @Autowired
    private TArticleTagMapper articleTagMapper;

    public int saveArticle(Article article) {
        return articleMapper.insert(article);
    }

    public int saveArticleCategory(TArticleCategory articleCategory) {
        return articleCategoryMapper.insert(articleCategory);
    }

    public int saveArticleTag(TArticleTag articleTag) {
        return articleTagMapper.insert(articleTag);
    }

    public Article getArticleById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article != null) {
            article.setViews(article.getViews() + 1);
            articleMapper.updateByPrimaryKey(article);
        }
        return article;
    }

    public Article getHotNews() {
        List<TSetting> settings = settingMapper.selectByKey("hotnews");
        if (settings != null && settings.size() > 0) {
            String hotnew = settings.get(0).getValue();
            Long newsid = Long.valueOf(hotnew);
            Article article = getArticleById(newsid);
            article.setContent(delTagsUtil.getTextFromHtml(article.getContent()));
            return article;
        } else {
            return null;
        }
    }

    /**
     * 获取其他相关文章
     *
     * @param article
     * @return
     */
    public List<Article> getOtherRelated(Article article) {
        //获取文章的分类
        TTag tag = tagMapper.selectByArticleId(article.getId());
        if (tag == null) {
            return null;
        }
        //获取分类下其他文章
        return articleMapper.selectByTagIdAndArticleId(tag.getId(), article.getId());
    }

    public List<Article> selectAllArticle() {
        return selectAllArticle(1);
    }

    public List<Article> selectAllArticle(int page) {
        return selectAllArticle(page, 10);
    }

    public List<Article> selectTopViewArticle(int page, int rows) {
        PageHelper.startPage(page, rows);
        return articleMapper.selectTopViewArticle();
    }

    public List<Article> selectAllArticle(int page, int rows) {
        PageHelper.startPage(page, rows);
        return articleMapper.selectAllArticle();
    }

    public void selectAllArticle(EasyuiDatagrid easyuiDatagrid, int page, int rows) {
        Page pages = PageHelper.startPage(page, rows);
        easyuiDatagrid.setRows(articleMapper.selectAllArticle());
        easyuiDatagrid.setTotal(pages.getTotal());
    }

    public List<Article> selectArticleListByTag(TTag tag, int page) {
        return selectArticleListByTag(tag, page, 10);
    }

    public List<Article> selectArticleListByTag(TTag tag, int page, int rows) {
        PageHelper.startPage(page, rows);
        return articleMapper.selectArticleListByTag(tag);
    }

    public List<Article> selectArticleListByCat(TCategory cat, int page) {
        return selectArticleListByCat(cat, page, 10);
    }

    public List<Article> selectArticleListByCat(TCategory cat, int page, int rows) {
        PageHelper.startPage(page, rows);
        return articleMapper.selectArticleListByCat(cat);
    }

    public List<TTag> getAllTag() {
        return tagMapper.selectAll();
    }

    public List<TCategory> getAllCategory() {
        return categoryMapper.selectAll();
    }

    public TTag getTagByEnName(String enName) {
        return tagMapper.selectByTagEnName(enName);
    }

    public TCategory getCatByEnName(String enName) {
        return categoryMapper.selectByCatEnName(enName);
    }

    public List<Menu> getSubMenu(String ename) {
        List<Menu> menus = null;
        //获取所有分类
        List<TCategory> categoryList = getAllCategory();
        if (categoryList != null && categoryList.size() > 0) {
            menus = new ArrayList<>();
        }
        for (TCategory cat : categoryList) {
            Menu menu = new Menu();
            menu.setName(cat.getZhName());
            menu.setUrl("/cat/" + cat.getEnName());
            if (ename != null && ename.trim().toLowerCase().equals(cat.getEnName())) {
                menu.setActivity(true);
            }
            menus.add(menu);
        }
        return menus;
    }

    public void setCloudTag(ModelAndView mv) {
        //获取标签云
        List<TTag> tagLiat = getAllTag();
        mv.addObject("tagcloud", tagLiat);
        //获取所有分类
        List<TCategory> categoryList = getAllCategory();
        mv.addObject("categorys", categoryList);
    }
}
