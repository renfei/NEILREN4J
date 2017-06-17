package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.IKAnalyzerService;
import com.neilren.neilren4j.modules.article.dao.ArticleDao;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章搜索服务
 *
 * @author NeilRen
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SearchService {
    private static String memcachedSearchArticleKey = "SearchArticleKey_word_";
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private IKAnalyzerService ikAnalyzerService;

    public List<ArticleWithBLOBs> Search(String wd) throws Exception {
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
            System.out.println(stringBuffer);
            articleWithBLOBsList = articleDao.searchByLike(stringBuffer.toString());
            memcachedManager.set(memcachedSearchArticleKey, articleWithBLOBsList, Global.MemcachedExpire);
        }
        return articleWithBLOBsList;
    }
}
