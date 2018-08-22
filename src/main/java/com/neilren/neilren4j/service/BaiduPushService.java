package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BaiduPushService
 * @Description 百度实时推送服务
 * @Date 2018/8/5 18:11
 */
@Service
public class BaiduPushService extends BaseService {
    @Autowired
    private Neilren4jConfig neilren4jConfig;

    /**
     * 提交给百度站长平台
     *
     * @param param
     * @param isOriginal
     * @return
     * @throws IOException
     */
    public String pushBaidu(String[] param, boolean isOriginal) throws IOException {
        String url = "http://data.zz.baidu.com/urls?site=%s&token=%s";
        url += isOriginal ? "&type=original" : "";
        url = String.format(url, neilren4jConfig.getBaidu().getSite(), neilren4jConfig.getBaidu().getToken());
        return new HttpClientService().sendPost(url, param);
    }

    /**
     * 提交给熊掌号
     *
     * @param param
     * @return
     * @throws IOException
     */
    public String pushXingzhang(String[] param) throws IOException {
        String url = "http://data.zz.baidu.com/urls?appid=%s&token=%s&type=realtime";
        url = String.format(url, neilren4jConfig.getBaidu().getXiongzhangappid(), neilren4jConfig.getBaidu().getXiongzhangtoken());
        return new HttpClientService().sendPost(url, param);
    }
}
