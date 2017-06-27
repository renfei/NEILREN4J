package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.common.service.PagingService;
import com.neilren.neilren4j.modules.article.dao.ArticleTagDao;
import com.neilren.neilren4j.modules.article.dao.TagDao;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.article.entity.ArticleTag;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.entity.Tag;
import freemarker.ext.beans.HashAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * 标签Service
 * Created by neil on 13/06/2017.
 */
@Service
@Transactional(readOnly = true)
public class TagService extends BaseService {

    private static String memcachedArticleListByTagKey = "ArticleListByTagKey_";
    private static String memcachedArticleByTagPagingListKey = "ArticleByTagPagingListKey_";
    private static String memcachedTagAllKey = "Tag_all";
    @Autowired
    private TagDao tagDao;
    @Autowired
    private ArticleTagDao articleTagDao;
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private PagingService pagingService;

    /**
     * 获取所有文章标签
     *
     * @return 标签List
     */
    public List<Tag> getAllTag() {
        List<Tag> tagList = null;
        tagList = (List<Tag>) memcachedManager.get(memcachedTagAllKey);
        if (tagList == null) {
            tagList = tagDao.selectAllTag();
            memcachedManager.set(memcachedTagAllKey, tagList, Global.MemcachedExpire);
        }
        return tagList;
    }

    /**
     * 获取文章列表ByTag
     *
     * @param index 页数
     * @param size  每页数量
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleList(String tag, int index, int size) {
        if (index <= 0) return null;
        index = (index - 1) * size;
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedArticleListByTagKey + index + "_size" + size + "_tag" + tag);
        if (articleWithBLOBsList == null) {
            try {
                Map<String, Object> stringObjectMap = new HashMap<String, Object>();
                stringObjectMap.put("tag", tag);
                stringObjectMap.put("index", index);
                stringObjectMap.put("size", size);
                articleWithBLOBsList = tagDao.selectByTagLimit(stringObjectMap);
                memcachedManager.set(memcachedArticleListByTagKey + index + "_size" + size + "_tag" + tag, articleWithBLOBsList, Global.MemcachedExpire);
            } catch (Exception e) {
            }
        }
        return articleWithBLOBsList;
    }

    /**
     * 获取文章列表ByTag
     *
     * @param index 页数
     * @return 文章List
     */
    public List<ArticleWithBLOBs> getArticleList(String tag, int index) {
        return getArticleList(tag, index, 10);
    }

    /**
     * 获取文章分页的页码
     *
     * @param index
     * @return
     */
    public List<ArticlePaging> getArticlePagingList(String tag, int index) {
        if (index <= 0) return null;
        List<ArticlePaging> articlePagingList = null;
        articlePagingList = (List<ArticlePaging>) memcachedManager.get(memcachedArticleByTagPagingListKey + "_" + index + "_tag" + tag);
        if (articlePagingList == null) {
            int total = tagDao.selectArticleTotal(tag);
            articlePagingList = pagingService.getPaging(index, total);
            memcachedManager.set(memcachedArticleByTagPagingListKey + "_" + index + "_tag" + tag, articlePagingList, Global.MemcachedExpire);
        }
        return articlePagingList;
    }

    /**
     * 插入文章标签关系
     * @param record
     */
    public void insterArticleTag(ArticleTag record){
        articleTagDao.insert(record);
    }
}
