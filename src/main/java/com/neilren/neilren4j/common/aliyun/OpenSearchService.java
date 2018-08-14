package com.neilren.neilren4j.common.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.aliyun.opensearch.object.KeyTypeEnum;
import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import com.neilren.neilren4j.entity.Search;
import com.neilren.neilren4j.entity.aliopenserach.OpenSearchResult;
import com.neilren.neilren4j.entity.aliopenserach.ResultsItems;
import com.neilren.neilren4j.service.interfaces.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName OpenSearchService
 * @Description TODO
 * @Date 2018/7/27 15:27
 */
@Slf4j
@Service
public class OpenSearchService implements SearchService {
    @Autowired
    private Neilren4jConfig neilren4jConfig;

    public List<Search> search(String wd, int page, int rows) {
        String accesskey = neilren4jConfig.getAliyun().getAccesskey();
        String secret = neilren4jConfig.getAliyun().getSecret();
        String appName = neilren4jConfig.getAliyun().getOpenSearch().getAppname();
        String host = neilren4jConfig.getAliyun().getOpenSearch().getHost();
        Map<String, Object> opts = new HashMap<String, Object>();
        int start = (page - 1) * rows;
        try {
            // 这里的host需要根据访问应用详情页中提供的的API入口来确定
            CloudsearchClient client = new CloudsearchClient(accesskey, secret, host, opts, KeyTypeEnum.ALIYUN);
            CloudsearchSearch search = new CloudsearchSearch(client);
            // 添加指定搜索的应用：
            search.addIndex(appName);
            // 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引到”字段。）
            search.setQueryString(wd);
            search.setStartHit(start);
            search.setHits(rows);
            // 指定搜索返回的格式。
            search.setFormat("json");
            // 设定过滤条件
//        search.addFilter("price>10");
            // 设定排序方式 + 表示正序 - 表示降序
//        search.addSort("price", "+");
            // 返回搜索结果
            OpenSearchResult openSearchResult = JSON.parseObject(search.search(), OpenSearchResult.class);
            List<Search> searches = new ArrayList<>();
            for (ResultsItems item : openSearchResult.getResult().getItems()) {
                Search se = new Search();
                se.setId(Long.valueOf(item.getId()));
                se.setTitle(item.getTitle());
                se.setContent(item.getContent());
                se.setPageview(item.getPageview());
                se.setTypeid(item.getTypeid());
                se.setDate(item.getTimestamp());
                se.setSearchtime(openSearchResult.getResult().getSearchtime());
                se.setTotal(openSearchResult.getResult().getTotal());
                searches.add(se);
            }
            return searches;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
