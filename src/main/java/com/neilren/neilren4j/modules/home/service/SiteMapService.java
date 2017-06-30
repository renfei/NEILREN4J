package com.neilren.neilren4j.modules.home.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.entity.Changefreq;
import com.neilren.neilren4j.common.entity.SiteMapXml;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.entity.ArticleArchives;
import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.entity.Tag;
import com.neilren.neilren4j.modules.article.service.ArticleService;
import com.neilren.neilren4j.modules.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 网站地图服务
 */
@Service
public class SiteMapService extends BaseService {
    private static String memcachedSiteMapstKey = "SiteMapstKey";
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private MemcachedManager memcachedManager;

    /**
     * 获取网站地图
     *
     * @return
     */
    public List<SiteMapXml> getSiteMaps() {
        List<SiteMapXml> siteMapXmls = (List<SiteMapXml>) memcachedManager.get(memcachedSiteMapstKey);
        if (siteMapXmls == null) {
            List<List<ArticleWithBLOBs>> articleWithBLOBs = new ArrayList<List<ArticleWithBLOBs>>();
            //首页
            siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site"), Changefreq.daily, "1"));
            //静态页
            siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/About", Changefreq.monthly, "0.95"));
            siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/Contact", Changefreq.monthly, "0.95"));
            siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/Subscribe", Changefreq.monthly, "0.95"));
            //首页分页
            List<ArticlePaging> articlePagings = articleService.getArticlePagingList(1);
            int lastIndex = articlePagings.get(articlePagings.size() - 1).getIndex();
            for (int i = 1; i <= lastIndex; i++) {
                siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/page/" + i, Changefreq.daily, "0.8"));
                articleWithBLOBs.add(articleService.getArticleList(i));
            }
            //标签页
            List<Tag> tags = tagService.getAllTag();
            for (Tag tag : tags) {
                siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/tag/" + tag.getEnName(), Changefreq.daily, "1"));
            }
            //文章页
            for (List<ArticleWithBLOBs> list : articleWithBLOBs) {
                for (ArticleWithBLOBs obj : list) {
                    siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/Article/" +
                            obj.getId(), Changefreq.monthly, "0.9", obj.getArticleDat()));
                }
            }
            //文章归档页
            List<ArticleArchives> articleArchives = articleService.getArticleArchives();
            for (ArticleArchives obj : articleArchives) {
                if (new SimpleDateFormat("yyyy-MM").format(new Date()) == obj.getDateYmd()) {
                    //当月的归档
                    siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/Archives/" + obj.getDateYmd(), Changefreq.daily, "0.7"));
                }
                siteMapXmls.add(new SiteMapXml(Global.getConfig("neilren.site") + "/Archives/" + obj.getDateYmd(), Changefreq.yearly, "0.7"));
            }
            memcachedManager.set(memcachedSiteMapstKey, siteMapXmls, Global.MemcachedExpire);
        }
        return siteMapXmls;
    }
}
