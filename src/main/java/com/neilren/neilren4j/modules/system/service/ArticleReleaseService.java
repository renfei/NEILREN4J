package com.neilren.neilren4j.modules.system.service;

import com.alibaba.fastjson.JSONArray;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaiduPushService;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.entity.ArticleCategory;
import com.neilren.neilren4j.modules.article.entity.ArticleTag;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.CategoryService;
import com.neilren.neilren4j.modules.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by neil on 28/06/2017.
 */
@Service
public class ArticleReleaseService extends BaseService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BaiduPushService baiduPushService;

    public ArticleWithBLOBs ArticleRelease(Map<String, Object> map) throws IOException {
        //插入文章
        ArticleWithBLOBs archiveWithBLOBs = new ArticleWithBLOBs();
        archiveWithBLOBs.setArticleDat(new Date());
        archiveWithBLOBs.setViews(0L);
        archiveWithBLOBs.setArticleStatus(0);
        archiveWithBLOBs.setArticleType(Integer.parseInt(map.get("type").toString()));
        archiveWithBLOBs.setTitle(map.get("title").toString());
        archiveWithBLOBs.setAuthor(map.get("author").toString());
        archiveWithBLOBs.setAuthorUrl(map.get("author_link").toString());
        archiveWithBLOBs.setSourceUrl(map.get("source").toString());
        archiveWithBLOBs.setKeyword(map.get("keyword").toString());
        archiveWithBLOBs.setDescribes(map.get("describes").toString());
        archiveWithBLOBs.setContent(map.get("content").toString());
        Long id = articleService.insterArticle(archiveWithBLOBs);
        //插入标签关系
        JSONArray jsonTagArray = (JSONArray) map.get("tag");
        for (int i = 0; i < jsonTagArray.size(); i++) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(archiveWithBLOBs.getId());
            articleTag.setTagId(Long.parseLong(jsonTagArray.getString(i)));
            tagService.insterArticleTag(articleTag);
        }
        //插入分类关系
        JSONArray jsonCatArray = (JSONArray) map.get("cat");
        for (int i = 0; i < jsonCatArray.size(); i++) {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setArticleId(archiveWithBLOBs.getId());
            articleCategory.setCategoryId(Long.parseLong(jsonCatArray.getString(i)));
            articleService.insterArticleCat(articleCategory);
        }
        //向百度主动推送
        baiduPushService.pushBaidu(new String[]{Global.getConfig("neilren.site") + "/Article/" + archiveWithBLOBs.getId()},
                archiveWithBLOBs.getArticleType() == 0 ? true : false);
        return archiveWithBLOBs;
    }
}
