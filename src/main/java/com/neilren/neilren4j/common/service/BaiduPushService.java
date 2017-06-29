package com.neilren.neilren4j.common.service;

import com.neilren.neilren4j.common.config.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 百度实时推送服务
 */
@Service
public class BaiduPushService extends BaseService {
    @Autowired
    private HttpClientService httpClientService;

    public String pushBaidu(String[] param, boolean isOriginal) throws IOException {
        String url = "http://data.zz.baidu.com/urls?site={0}&token={1}";
        url += isOriginal ? "&type=original" : "";
        url = String.format(url, Global.getConfig("baidu.site"), Global.getConfig("baidu.token"));
        return httpClientService.sendPost(url, param);
    }
}
