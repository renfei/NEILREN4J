package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.modules.article.dao.ArticleDao;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 14/06/2017.
 */
@Service
@Transactional(readOnly = true)
public class SearchService {
    private static String memcachedSearchArticleKey = "SearchArticleKey_word_";
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private MemcachedManager memcachedManager;

    public List<ArticleWithBLOBs> Search(String wd) throws IOException {
        IKAnalysis(wd);
        List<ArticleWithBLOBs> articleWithBLOBsList = null;
        articleWithBLOBsList = (List<ArticleWithBLOBs>) memcachedManager.get(memcachedSearchArticleKey + wd);
        StringBuffer stringBuffer = new StringBuffer();
        if (articleWithBLOBsList == null) {
            //分词
            List<String> stringList = IKAnalysis(wd);
            for (String word : stringList) {
                stringBuffer.append(word + "%");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            System.out.println(stringBuffer);
            articleWithBLOBsList = articleDao.searchByLike(stringBuffer.toString());
            memcachedManager.set(memcachedSearchArticleKey, articleWithBLOBsList, Global.MemcachedExpire);
        }
        return articleWithBLOBsList;
    }

    public static List<String> IKAnalysis(String str) {
        List<String> keywordList = new ArrayList<String>();
        try {
            byte[] bt = str.getBytes();
            InputStream ip = new ByteArrayInputStream(bt);
            Reader read = new InputStreamReader(ip);
            IKSegmenter iks = new IKSegmenter(read, true);//true开启只能分词模式，如果不设置默认为false，也就是细粒度分割
            Lexeme t;
            while ((t = iks.next()) != null) {
                keywordList.add(t.getLexemeText());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(keywordList);
        return keywordList;
    }
}
