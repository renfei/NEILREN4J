package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.dbentity.TArticle;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.io.IOException;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ESService
 * @Description TODO
 * @Date 2018/7/21 15:46
 */
@Slf4j
@Service
public class ESService extends BasePageService {
    @Autowired
    private JestClient jestClient;

    public void saveEntity(TArticle article) {
        Index index = new Index.Builder(article).index(article.getId().toString()).type("article").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 批量保存内容到ES
     */
    public void saveEntity(List<TArticle> articleList) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (TArticle article : articleList) {
            Index index = new Index.Builder(article).index(article.getId().toString()).type("article").build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 在ES中搜索内容
     */
    public List<TArticle> searchEntity(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
        //searchSourceBuilder.field("name");
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("index_article").addType("article").build();
        try {
            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(TArticle.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
