package com.neilren.neilren4j.modules.search.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.service.PagingService;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.search.entity.OpenSearchResult;

import com.alibaba.fastjson.JSON;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.search.*;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.search.entity.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.opensearch.*;
import com.aliyun.opensearch.sdk.dependencies.com.google.common.collect.Lists;
import com.aliyun.opensearch.sdk.generated.OpenSearch;

import java.util.List;

/**
 * 搜索服务
 * Created by neil on 06/07/2017.
 */
@Service
public class SearchService extends BaseService {
    @Autowired
    private PagingService pagingService;
    @Autowired
    private MemcachedManager memcachedManager;

    private static String memcachedOpenSearchArticleKey = "OpenSearchArticleKey_word_";

    String appName = "NEILREN";
    String accesskey = Global.getConfig("aliyun.accessKeyId");
    String secret = Global.getConfig("aliyun.accessKeySecret");
    String host = Global.getConfig("aliyun.opensearch.host");
    //创建并构造OpenSearch对象
    OpenSearch openSearch = new OpenSearch(accesskey, secret, host);
    //创建OpenSearchClient对象，并以OpenSearch对象作为构造参数
    OpenSearchClient serviceClient = new OpenSearchClient(openSearch);
    //创建SearcherClient对象，并以OpenSearchClient对象作为构造参数
    SearcherClient searcherClient = new SearcherClient(serviceClient);
    //定义Config对象，用于设定config子句参数，指定应用名，分页，数据返回格式等等
    Config config = new Config(Lists.newArrayList(appName));

    /**
     * 搜索，使用阿里云开放搜索引擎
     *
     * @param wd    搜索词
     * @param index 当前页数
     * @return
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public Results Search(String wd, int index) throws OpenSearchClientException, OpenSearchException {
        Results results = null;
        results = (Results) memcachedManager.get(memcachedOpenSearchArticleKey + wd + "_" + index);
        if (results == null) {
            List<ArticleWithBLOBs> obj = null;
            if (index < 1)
                return null;
            config.setStart((index - 1) * 10);
            config.setHits(10);
            //设置返回格式为fulljson格式
            config.setSearchFormat(SearchFormat.JSON);
            // 创建参数对象
            SearchParams searchParams = new SearchParams(config);
            // 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引字段列表”中对应字段。），若需多个索引组合查询，需要在setQuery处合并，否则若设置多个setQuery子句，则后面的子句会替换前面子句
            searchParams.setQuery("default:'" + wd + "'");
            //设置查询过滤条件
            searchParams.setFilter("");
            //创建sort对象，并设置二维排序
            Sort sort = new Sort();
            //设置id字段降序
            sort.addToSortFields(new SortField("id", Order.DECREASE));
            //若id相同则以RANK相关性算分升序
            sort.addToSortFields(new SortField("RANK", Order.INCREASE));
            // 设置搜索结果摘要信息，此处采用下面的SearchParamsBuilder对象添加搜索结果摘要，比较简便
            Summary summ = new Summary("content");
            summ.setSummary_field("content");//指定的生效的字段。此字段必需为可分词的text类型的字段。
            summ.setSummary_len("200");//片段长度
            summ.setSummary_element("em"); //飘红标签
            summ.setSummary_ellipsis("...");//片段链接符
            summ.setSummary_snippet("2");//片段数量
            //添加Summary对象参数
            searchParams.addToSummaries(summ);
            Summary summ2 = new Summary("title");
            summ2.setSummary_field("title");//指定的生效的字段。此字段必需为可分词的text类型的字段。
            summ2.setSummary_len("100");//片段长度
            summ2.setSummary_element("em"); //飘红标签
            summ2.setSummary_ellipsis("...");//片段链接符
            summ2.setSummary_snippet("1");//片段数量
            //添加Summary对象参数
            searchParams.addToSummaries(summ2);
            try {
                // 执行返回查询结果
                SearchResult searchResult = searcherClient.execute(searchParams);
                String result = searchResult.getResult();
                OpenSearchResult openSearchResult = JSON.parseObject(result, OpenSearchResult.class);
                if (openSearchResult.getStatus().equals("OK")) {
                    results = openSearchResult.getResult();
                    memcachedManager.set(memcachedOpenSearchArticleKey + wd + "_" + index, results, Global.MemcachedExpire);
                    return results;
                } else
                    return null;
            } catch (OpenSearchException e) {
                e.printStackTrace();
            } catch (OpenSearchClientException e) {
                e.printStackTrace();
            }
            return null;
        } else
            return results;

    }

    /**
     * 获取搜索的翻页对象，默认每页10个元素
     *
     * @param index 当前页数
     * @param total 总元素数量
     * @return
     */
    public List<ArticlePaging> getSearchPagingList(int index, int total) {
        List<ArticlePaging> articlePagingList = null;
        articlePagingList = pagingService.getPaging(index, total);
        return articlePagingList;
    }
}
