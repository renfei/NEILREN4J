package com.neilren.neilren4j.entity;

import com.neilren.neilren4j.common.util.IpUtil;
import com.neilren.neilren4j.dbentity.TLogAccess;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName LogAccess
 * @Description TODO
 * @Date 2018/8/1 12:55
 */
@Data
public class LogAccess extends TLogAccess {
    public LogAccess(HttpServletRequest request) {
        IpUtil ipUtil = new IpUtil();
        this.setDatetime(new Date(System.currentTimeMillis()));
        this.setIp(ipUtil.getIpAddr(request));
        this.setReferer(request.getHeader("Referer"));
        this.setProtocol(request.getProtocol());
        this.setScheme(request.getScheme());
        this.setHostName(request.getServerName());
        this.setPort(String.valueOf(request.getServerPort()));
        this.setMethod(request.getMethod());
        this.setUrl(request.getRequestURL().toString());
        this.setQuery(request.getQueryString());
        this.setSessionId(request.getRequestedSessionId());
        this.setAccept(request.getHeader("Accept"));
        this.setAcceptLanguage(request.getHeader("Accept-Language"));
        this.setUserAgent(request.getHeader("User-Agent"));
        this.setCookie(request.getHeader("Cookie"));
        String agentStr = request.getHeader("user-agent");
        UserAgent agent = UserAgent.parseUserAgentString(agentStr);
        //浏览器
        Browser browser = agent.getBrowser();
        //浏览器版本
        Version version = agent.getBrowserVersion();
        //操作系统
        OperatingSystem os = agent.getOperatingSystem();
        if (browser != null) {
            this.setBrowserType(browser.getBrowserType().toString());
            this.setBrowserName(browser.getName());
            this.setBrowserManufacture(browser.getManufacturer().toString());
            this.setBrowserGroup(browser.getGroup().toString());
            this.setBrowserEngine(browser.getRenderingEngine().toString());
        }
        if (version != null) {
            this.setBrowserMajorVersion(version.getMajorVersion());
            this.setBrowserMinorVersion(version.getMinorVersion());
            this.setBrowserVersion(version.getVersion());
        }
        this.setOsName(os.getName());
        this.setOsType(os.getDeviceType().toString());
        this.setOsGroup(os.getGroup().toString());
        this.setOsManufacturer(os.getManufacturer().toString());
    }

}
