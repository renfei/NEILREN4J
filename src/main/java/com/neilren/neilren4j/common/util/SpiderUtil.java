package com.neilren.neilren4j.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SpiderUtil
 * @Description TODO
 * @Date 2018/8/1 10:53
 */
@Component
public class SpiderUtil {
    @Autowired
    private IpUtil ipUtil;
    @Autowired
    private ExecLinuxCMD execLinuxCMD;

    public boolean checkSpider(HttpServletRequest request) {
        boolean check = false;
        String ua = request.getHeader("User-Agent");
        String ip = ipUtil.getIpAddr(request);
        //[TODO]查找是否是已知的蜘蛛IP
        String host = execLinuxCMD.host(ip);
        return check;
    }
}
