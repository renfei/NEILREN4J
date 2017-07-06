package com.neilren.neilren4j.test;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.modules.api.entity.Article;
import com.neilren.neilren4j.modules.api.entity.WeChatReply;
import com.neilren.neilren4j.modules.article.entity.ArticleWithBLOBs;
import com.neilren.neilren4j.modules.article.service.SearchService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by neil on 19/06/2017.
 */
public class test {
    public static void main(String[] args) {
        //[TODO]...保存接受消息到数据库
        //拼装回复消息
        WeChatReply reply = new WeChatReply();
        reply.setCreateTime(new Date());
        try {
            //调用搜索服务
            List<ArticleWithBLOBs> articleWithBLOBss = new SearchService().Search("任霏", 1);
            List<Article> articles = new ArrayList<Article>();
            //循环装载5个搜索结果
            for (int i = 0; i < articleWithBLOBss.size() && i < 5; i++) {
                Article article = new Article();
                article.setTitle(articleWithBLOBss.get(i).getTitle());
                if (articleWithBLOBss.get(i).getDescribes().length() >= 200)
                    article.setDescription(
                            articleWithBLOBss.get(i).getDescribes() == "" ?
                                    articleWithBLOBss.get(i).getContent().substring(0, 200) :
                                    articleWithBLOBss.get(i).getDescribes()
                    );
                else
                    article.setDescription(
                            articleWithBLOBss.get(i).getDescribes() == "" ?
                                    articleWithBLOBss.get(i).getContent() :
                                    articleWithBLOBss.get(i).getDescribes()
                    );
                article.setUrl(Global.getConfig("neilren.site") + "/Article/" + articleWithBLOBss.get(i).getId());
                articles.add(article);
            }
            System.out.println("装载完毕");
            reply.setArticles(articles);
            reply.setMsgType(WeChatReply.NEWS);
            reply.setArticleCount(articles.size());
        } catch (Exception e) {
            reply.setMsgType(WeChatReply.TEXT);
            reply.setContent(WeChatReply.ERROR_CONTENT);
            System.out.println(e.getMessage());
        }
    }
}
