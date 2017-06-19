package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.common.service.PagingService;
import com.neilren.neilren4j.modules.article.dao.ArticleDao;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neil on 19/06/2017.
 */
@Service
@Transactional(readOnly = true)
public class ArchivesService extends BaseService {
    private static String memcachedArticleListByArchivesKey = "ArticleListByArchivesKey_";
    private static String memcachedArticleByArchivesPagingListKey = "ArticleByArchivesPagingListKey_";
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private PagingService pagingService;

    /**
     * 获取文章列表ByTag
     *
     * @param index 页数
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleList(String date, int index) {
        return getArticleList(date, index, 10);
    }

    /**
     * 获取文章列表ByTag
     *
     * @param index 页数
     * @param size  每页数量
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleList(String date, int index, int size) {
        if (index <= 0) return null;
        try {
            Date date_temp = new Date(date + "-01");
        } catch (Exception e) {
        }
        index = (index - 1) * size;
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedArticleListByArchivesKey + index + "_size" + size + "_date" + date);
        if (articleWithBLOBsList == null) {
            try {
                Map<String, Object> stringObjectMap = new HashMap<String, Object>();
                stringObjectMap.put("date", date);
                stringObjectMap.put("index", index);
                stringObjectMap.put("size", size);
                articleWithBLOBsList = articleDao.selectByArchivesLimit(stringObjectMap);
                memcachedManager.set(memcachedArticleListByArchivesKey + index + "_size" + size + "_date" + date, articleWithBLOBsList, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleWithBLOBsList;
    }

    /**
     * 获取文章分页的页码
     *
     * @param index
     * @return
     */
    public List<ArticlePaging> getArticlePagingList(String date, int index) {
        if (index <= 0) return null;
        List<ArticlePaging> articlePagingList = null;
        articlePagingList = (List<ArticlePaging>) memcachedManager.get(memcachedArticleByArchivesPagingListKey + "_" + index + "_date" + date);
        if (articlePagingList == null) {
            int total = articleDao.selectArticleTotalByArchives(date);
            articlePagingList = pagingService.getPaging(index, total);
            memcachedManager.set(memcachedArticleByArchivesPagingListKey + "_" + index + "_date" + date, articlePagingList, Global.MemcachedExpire);
        }
        return articlePagingList;
    }
}
