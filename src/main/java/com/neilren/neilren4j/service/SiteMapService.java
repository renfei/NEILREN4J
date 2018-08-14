package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.dbentity.TTag;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.entity.Changefreq;
import com.neilren.neilren4j.entity.SiteMapXml;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SiteMapService
 * @Description TODO
 * @Date 2018/7/31 18:16
 */
@Service
public class SiteMapService extends BasePageService {
    /**
     * 获取网站地图
     *
     * @return
     */
    public List<SiteMapXml> getSiteMaps() {
        List<SiteMapXml> siteMapXmls = null;
        if (siteMapXmls == null) {
            siteMapXmls = new ArrayList<SiteMapXml>();
            //首页
            siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost(), Changefreq.daily, "1"));
            //静态页
            siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/blog", Changefreq.daily, "1"));
            siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/about", Changefreq.monthly, "0.95"));
            siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/album", Changefreq.weekly, "0.92"));
            siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/maven", Changefreq.monthly, "0.92"));
            //标签页
            List<TTag> tags = articleService.getAllTag();
            for (TTag tag : tags) {
                siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/tag/" + tag.getEnName(), Changefreq.daily, "1"));
            }
            //文章页
            List<Article> articles = articleService.selectAllArticle(1, Integer.MAX_VALUE);
            for (Article article : articles) {
                siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/Article/" +
                        article.getId(), Changefreq.monthly, "0.9", article.getDate()));
            }
            //文章归档页
//            List<ArticleArchives> articleArchives = articleService.getArticleArchives();
//            for (ArticleArchives obj : articleArchives) {
//                if (new SimpleDateFormat("yyyy-MM").format(new Date()) == obj.getDateYmd()) {
//                    //当月的归档
//                    siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/Archives/" + obj.getDateYmd(), Changefreq.daily, "0.7"));
//                }
//                siteMapXmls.add(new SiteMapXml(neilren4jConfig.getHost() + "/Archives/" + obj.getDateYmd(), Changefreq.yearly, "0.7"));
//            }
        }
        return siteMapXmls;
    }
}
