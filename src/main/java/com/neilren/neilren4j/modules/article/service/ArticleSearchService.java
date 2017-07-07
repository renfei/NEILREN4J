package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.IKAnalyzerService;
import com.neilren.neilren4j.common.service.PagingService;
import com.neilren.neilren4j.modules.article.dao.ArticleDao;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章搜索服务
 *
 * @author NeilRen
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ArticleSearchService {
    private static String memcachedSearchArticleKey = "SearchArticleKey_word_";
    private static String memcachedSearchPagingKey = "SearchPagingKey_word_";
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private IKAnalyzerService ikAnalyzerService;
    @Autowired
    private PagingService pagingService;

    public List<ArticleWithBLOBs> Search(String wd, int index) throws Exception {
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedSearchArticleKey + wd);
        StringBuffer stringBuffer = new StringBuffer();
        if (articleWithBLOBsList == null) {
            //分词
            List<String> stringList = ikAnalyzerService.analyzer(wd);
            for (String word : stringList) {
                stringBuffer.append(word + "%");
            }
            if (stringBuffer.length() > 1)
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("index", index);
            map.put("size", 10);
            map.put("wd", stringBuffer.toString());
            articleWithBLOBsList = articleDao.searchByLike(map);
            memcachedManager.set(memcachedSearchArticleKey, articleWithBLOBsList, Global.MemcachedExpire);
        }
        return articleWithBLOBsList;
    }

    /**
     * 获取文章分页的页码
     *
     * @param index
     * @return
     */
    public List<ArticlePaging> getSearchPagingList(String wd, int index) {
        if (index <= 0) return null;
        List<ArticlePaging> articlePagingList = null;
        articlePagingList = (List<ArticlePaging>) memcachedManager.get(memcachedSearchPagingKey + wd + "_" + index);
        if (articlePagingList == null) {
            int total = articleDao.selectSearchArticleTotal(wd);
            articlePagingList = pagingService.getPaging(index, total);
            memcachedManager.set(memcachedSearchPagingKey + wd + "_" + index, articlePagingList, Global.MemcachedExpire);
        }
        return articlePagingList;
    }
}
