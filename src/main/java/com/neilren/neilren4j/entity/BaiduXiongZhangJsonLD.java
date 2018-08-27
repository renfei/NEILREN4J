package com.neilren.neilren4j.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BaiduXiongZhangJsonLD
 * @Description TODO
 * @Date 2018/8/14 15:44
 */
@Data
public class BaiduXiongZhangJsonLD {
    private String context;
    private String id;
    private String appid;
    private String title;
    private List<String> images;
    private Date pubDate;

    public BaiduXiongZhangJsonLD(String id, String title, List<String> images, Date pubDate, String appid) {
        this.context = "https://ziyuan.baidu.com/contexts/cambrian.jsonld";
        this.id = id;
        this.title = title;
        this.images = images;
        this.pubDate = pubDate;
        this.appid = appid;
    }

    public String getPubDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //加上时间
        return sDateFormat.format(pubDate);
    }

    public static String getHtmlCode(BaiduXiongZhangJsonLD baiduXiongZhangJsonLD) {
        String htmlCode = "";
        String json = JSON.toJSONString(baiduXiongZhangJsonLD);
        json = json.replace("\"context\"", "\"@context\"");
        json = json.replace("\"id\"", "\"@id\"");
        json = json.replace(",", ",\n");
        htmlCode += "<script type=\"application/ld+json\">\n";
        htmlCode += json;
        htmlCode += "\n</script>\n";
        return htmlCode;
    }
}
