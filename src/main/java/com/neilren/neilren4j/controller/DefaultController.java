package com.neilren.neilren4j.controller;

import com.neilren.neilren4j.common.baseclass.BasePageController;
import com.neilren.neilren4j.dbentity.TSetting;
import com.neilren.neilren4j.entity.Article;
import com.neilren.neilren4j.entity.HeadTitle;
import com.neilren.neilren4j.entity.SiteMapXml;
import com.neilren.neilren4j.service.FrielinkService;
import com.neilren.neilren4j.service.SiteMapService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName DefaultController
 * @Description TODO
 * @Date 2018/7/16 16:59
 */
@Controller
public class DefaultController extends BasePageController {
    @Autowired
    private SiteMapService siteMapService;
    @Autowired
    private FrielinkService frielinkService;
    @Value("classpath:xml/sitemap.xsl")
    private Resource sitemapxslXml;

    @RequestMapping("/")
    public ModelAndView defaultPage() {
        ModelAndView mv = new ModelAndView();
        List<Article> articles = articleService.selectAllArticle(1);
        List<Article> articlestop3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            articlestop3.add(articles.get(i));
        }
        articles.remove(0);
        articles.remove(0);
        articles.remove(0);
        String description = null;
        List<TSetting> descriptions = systemService.getSettingByKey("description");
        if (descriptions != null && descriptions.size() > 0) {
            description = descriptions.get(0).getValue();
        }
        String keyword = null;
        List<TSetting> keywords = systemService.getSettingByKey("keywords");
        if (keywords != null && keywords.size() > 0) {
            keyword = keywords.get(0).getValue();
        }
        HeadTitle headTitle = new HeadTitle(siteName, description, keyword);
        mv.addObject("title", headTitle);
        mv.addObject("hotnews", articleService.getHotNews());
        mv.addObject("articlestop3", articlestop3);
        mv.addObject("articles", articles);
        mv.addObject("type", "blog");
        mv.addObject("frieLinkList", frielinkService.getValidFrieLinks());
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "robots.txt", produces = "text/plain")
    @ResponseBody
    public String getRobotsTxt() {
        String robots = "#\n" +
                "# robots.txt for NEILREN.COM\n" +
                "# Version 4.0.0\n" +
                "#\n" +
                "\n" +
                "User-agent: *\n" +
                "\n" +
                "Disallow: /Search?\n" +
                "\n" +
                "Sitemap: " + neilren4jConfig.getHost() + "/sitemap.xml";
        return robots;
    }

    @RequestMapping(value = "sitemap.xml", produces = "text/xml;charset=UTF-8")
    @ResponseBody
    public String getSiteMapXml() throws ParseException {
        List<SiteMapXml> siteMapXmls = siteMapService.getSiteMaps();
        String xml = "    <url>\n" +
                "        <loc>%s</loc>\n" +
                "        <mobile type=\"pc,mobile\"/>\n" +
                "        <changefreq>%s</changefreq>\n" +
                "        <priority>%s</priority>\n" +
                "        <lastmod>%s</lastmod>\n" +
                "    </url>\n";
        StringBuffer resxml = new StringBuffer();
        resxml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        resxml.append("<?xml-stylesheet type=\"text/xsl\" href=\"" + neilren4jConfig.getHost() + "/sitemap.xsl\"?>\n");
        resxml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n");
        resxml.append("    xmlns:mobile=\"http://www.google.com/schemas/sitemap-mobile/1.0\">\n");
        for (SiteMapXml s : siteMapXmls) {
            resxml.append(String.format(xml, s.getLoc(), s.getChangefreq(), s.getPriority(), s.getLastmod()));
        }
        resxml.append("</urlset>\n");
        return resxml.toString();
    }

    @RequestMapping(value = "sitemap.xsl", produces = "application/octet-stream;charset=UTF-8")
    @ResponseBody
    public String getSiteMapXsl(HttpServletResponse response) throws IOException {
        String sitemapxsl = IOUtils.toString(sitemapxslXml.getInputStream());
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        return sitemapxsl;
    }
}
