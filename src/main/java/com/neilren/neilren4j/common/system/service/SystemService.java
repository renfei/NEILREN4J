package com.neilren.neilren4j.common.system.service;

import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by neil on 07/07/2017.
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService {
    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    Welcome to " + Global.getConfig("productName") + "  - Powered By http://www.neilren.com\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }
}
