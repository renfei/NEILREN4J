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
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
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
@Lazy
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

    /**
     * 根据ID获取文章
     *
     * @param id ID编号
     * @return 文章
     */
    public Article getArticleById(Long id) {

        //先从缓存中获取，如果没有就从数据库中获取
        Article article = (Article) cacheGet("article", id.toString(), () -> articleMapper.selectByPrimaryKey(id), defaultCacheTime);

        //拿到以后，浏览量加一
        if (article != null) {
            setViewAdd(id);
        }
        return article;
    }

    @Async
    public int setViewAdd(Long id) {
        return articleMapper.updateViewById(id);
    }

    public Article getHotNews() {
        Article article = (Article) cacheGet("article", "hotnews", () -> {
            List<TSetting> settings = settingMapper.selectByKey("hotnews");
            if (settings != null && settings.size() > 0) {
                String hotnew = settings.get(0).getValue();
                Long newsid = Long.valueOf(hotnew);
                Article a = getArticleById(newsid);
                a.setContent(delTagsUtil.getTextFromHtml(a.getContent()));
                return a;
            } else {
                return null;
            }
        }, defaultCacheTime);
        return article;
    }

    /**
     * 获取其他相关文章
     *
     * @param article
     * @return
     */
    public List<Article> getOtherRelated(Article article) {
        List<Article> articles = (List<Article>) cacheGet("articles", "other_" + article.getId(), () -> {
            //获取文章的分类
            TTag tag = tagMapper.selectByArticleId(article.getId());
            if (tag == null) {
                return null;
            } else {
                List<Article> a = articleMapper.selectByTagIdAndArticleId(tag.getId(), article.getId());
                return a;
            }
        }, defaultCacheTime);
        return articles;
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
        return (List<Article>) cacheGet("Article", page + "_" + rows, () -> {
            PageHelper.startPage(page, rows);
            return articleMapper.selectAllArticle();
        }, defaultCacheTime);
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
        return (List<Article>) cacheGet("Article_Tag_" + tag.getEnName(), page + "_" + rows, () -> {
            PageHelper.startPage(page, rows);
            return articleMapper.selectArticleListByTag(tag);
        }, defaultCacheTime);
    }

    public List<Article> selectArticleListByCat(TCategory cat, int page) {
        return selectArticleListByCat(cat, page, 10);
    }

    public List<Article> selectArticleListByCat(TCategory cat, int page, int rows) {
        return (List<Article>) cacheGet("Article_cat_" + cat.getEnName(), page + "_" + rows, () -> {
            PageHelper.startPage(page, rows);
            return articleMapper.selectArticleListByCat(cat);
        }, defaultCacheTime);

    }

    public List<TTag> getAllTag() {
        return (List<TTag>) cacheGet("AllTag", "", () -> tagMapper.selectAll(), defaultCacheTime);
    }

    public List<TCategory> getAllCategory() {
        return (List<TCategory>) cacheGet("AllTag", "", () -> categoryMapper.selectAll(), defaultCacheTime);
    }

    public TTag getTagByEnName(String enName) {
        return (TTag) cacheGet("Tag", enName, () -> tagMapper.selectByTagEnName(enName), defaultCacheTime);
    }

    public TCategory getCatByEnName(String enName) {
        return (TCategory) cacheGet("Cat", enName, () -> categoryMapper.selectByCatEnName(enName), defaultCacheTime);
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
