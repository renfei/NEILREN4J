package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.common.service.PagingService;
import com.neilren.neilren4j.modules.article.dao.ArticleDao;
import com.neilren.neilren4j.modules.article.dao.ArticleGradeDao;
import com.neilren.neilren4j.modules.article.entity.ArticleArchives;
import com.neilren.neilren4j.modules.article.entity.ArticleGrade;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    private static String memcachedArticleTop10ByViewsListKey = "ArticleTop10ByViewsList";
    private static String memcachedArticleKey = "Article_id";
    private static String memcachedArticleArchivesListKey = "ArticleArchivesList";
    private static String memcachedArticlePagingListKey = "ArticlePagingList";

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleGradeDao articleGradeDao;
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private PagingService pagingService;

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
        if (articleWithBLOBs != null)
            articleDao.updateViewsByPrimaryKey(longID);
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
                articleWithBLOBsList = articleDao.selectTop10ByViews();
                memcachedManager.set(memcachedArticleTop10ByDateListKey, articleWithBLOBsList, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleWithBLOBsList;
    }

    /**
     * 获取阅读量前十文章
     *
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleTop10ByViewsList() {
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedArticleTop10ByViewsListKey);
        if (articleWithBLOBsList == null) {
            try {
                articleWithBLOBsList = articleDao.selectTop10ByDate();
                memcachedManager.set(memcachedArticleTop10ByViewsListKey, articleWithBLOBsList, Global.MemcachedExpire);
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
            int total = articleDao.selectArticleTotal();
            articlePagingList = pagingService.getPaging(index, total);
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

    public int setArticleGrade(String longId, String grade) {
        int Grade = 5;
        long ID = 0l;
        try {
            Grade = Integer.parseInt(grade);
            ID = Long.parseLong(longId);
        } catch (Exception e) {
            return -1;
        }
        if (Grade > 0 && Grade < 6 && ID > 1000000) {
            ArticleGrade articleGrade = new ArticleGrade();
            articleGrade.setArticleId(ID);
            articleGrade.setGrade(Grade);
            articleGrade.setAddDate(new Date());
            return articleGradeDao.insert(articleGrade);
        } else return -1;
    }
}
