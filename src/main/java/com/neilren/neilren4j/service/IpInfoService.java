package com.neilren.neilren4j.service;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import com.neilren.neilren4j.common.util.HttpUtils;
import com.neilren.neilren4j.entity.AlicmapiIP;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName IpInfoService
 * @Description TODO
 * @Date 2018/8/20 10:54
 */
@Slf4j
@Service
public class IpInfoService extends BaseService {
    @Autowired
    private Neilren4jConfig neilren4jConfig;

    public AlicmapiIP getIpInfo(String ip) {
        String host = "https://api01.aliyun.venuscn.com";
        String path = "/ip";
        String method = "GET";
        String appcode = neilren4jConfig.getAliyun().getApiappcodeip();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ip", ip.trim());


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//            System.out.println(response.toString());
            //获取response的body
            AlicmapiIP alicmapiIP = JSON.parseObject(EntityUtils.toString(response.getEntity()), AlicmapiIP.class);
            if (alicmapiIP.getRet() == 200) {
                return alicmapiIP;
            } else {
                return null;
            }
//            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
