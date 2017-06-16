package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.dao.ArticleDao;
import com.neilren.neilren4j.modules.article.entity.ArticleArchives;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章Service
 *
 * @author NeilRen
 * @version 2017-06-07
 */
@Service
@Transactional(readOnly = true)
public class ArticleService extends BaseService {

    private static String memcachedArticleListKey = "ArticleList_index";
    private static String memcachedArticleTop10ByDateListKey = "ArticleTop10ByDateList";
    private static String memcachedArticleKey = "Article_id";
    private static String memcachedArticleArchivesListKey = "ArticleArchivesList";
    private static String memcachedArticlePagingListKey = "ArticlePagingList";
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private MemcachedManager memcachedManager;

    /**
     * 获取指定的文章
     *
     * @param id 文章ID
     * @return
     */
    public ArticleWithBLOBs findById(String id) {
        Long longID;
        try {
            longID = Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }
        ArticleWithBLOBs articleWithBLOBs = null;
        articleWithBLOBs = (ArticleWithBLOBs) memcachedManager.get(memcachedArticleKey + longID);
        if (articleWithBLOBs == null) {
            try {
                articleWithBLOBs = articleDao.selectByPrimaryKey(longID);
                memcachedManager.set(memcachedArticleKey + longID, articleWithBLOBs, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleWithBLOBs;
    }

    /**
     * 获取文章列表
     *
     * @param index 页数
     * @param size  每页数量
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleList(int index, int size) {
        if (index <= 0) return null;
        index = (index - 1) * size;
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedArticleListKey + index + "_size" + size);
        if (articleWithBLOBsList == null) {
            try {
                articleWithBLOBsList = articleDao.selectByLimit(index, size);
                memcachedManager.set(memcachedArticleListKey + index + "_size" + size, articleWithBLOBsList, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleWithBLOBsList;
    }

    /**
     * 获取10篇最新文章列表
     *
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleTop10ByDateList() {
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedArticleTop10ByDateListKey);
        if (articleWithBLOBsList == null) {
            try {
                articleWithBLOBsList = articleDao.selectTop10ByDate();
                memcachedManager.set(memcachedArticleTop10ByDateListKey, articleWithBLOBsList, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleWithBLOBsList;
    }

    /**
     * 获取文章列表
     *
     * @param index 页数
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleList(int index) {
        return getArticleList(index, 10);
    }

    /**
     * 获取文章分页的页码
     *
     * @param index
     * @return
     */
    public List<ArticlePaging> getArticlePagingList(int index) {
        if (index <= 0) return null;
        List<ArticlePaging> articlePagingList = null;
        articlePagingList = (List<ArticlePaging>) memcachedManager.get(memcachedArticlePagingListKey + "_" + index);
        if (articlePagingList == null) {
            int front = 4;
            int total = articleDao.selectArticleTotal();
            total = (int) Math.ceil(total / 10);
            articlePagingList = new ArrayList<ArticlePaging>();
            articlePagingList.add(new ArticlePaging("首页", 1));
            if (index > total - 3) {
                //后方不足数
                front = 6 - (total - index);
            }
            List<ArticlePaging> tempList = new ArrayList<ArticlePaging>();
            //从当前页往前取3个，但不能成负数
            for (int i = 0, j = index; i < front && j > 0; i++, j--) {
                tempList.add(new ArticlePaging(String.valueOf(j), j));
            }
            //顺序修正，需要倒序
            for (int i = tempList.size() - 1; i >= 0; i--) {
                articlePagingList.add(tempList.get(i));
            }
            //如果不满8个，就一直加，但是不能加的超过总页数
            for (int i = articlePagingList.size(), j = index + 1; i < 8 && j <= total; i++, j++) {
                articlePagingList.add(new ArticlePaging(String.valueOf(j), j));
            }
            articlePagingList.add(new ArticlePaging("末页", total));
            memcachedManager.set(memcachedArticlePagingListKey + "_" + index, articlePagingList, Global.MemcachedExpire);
        }
        return articlePagingList;
    }

    /**
     * 获取文章归档
     *
     * @return
     */
    public List<ArticleArchives> getArticleArchives() {
        List<ArticleArchives> articleArchivesList = null;
        articleArchivesList = (List<ArticleArchives>) memcachedManager.get(memcachedArticleArchivesListKey);
        if (articleArchivesList == null) {
            try {
                articleArchivesList = articleDao.selectArchives();
                memcachedManager.set(memcachedArticleArchivesListKey, articleArchivesList, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleArchivesList;
    }
}
