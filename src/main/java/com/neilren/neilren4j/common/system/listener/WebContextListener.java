package com.neilren.neilren4j.common.system.listener;

import com.neilren.neilren4j.common.system.service.SystemService;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by neil on 07/07/2017.
 */
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        if (!SystemService.printKeyLoadMessage()){
            return null;
        }
        return super.initWebApplicationContext(servletContext);
    }
}
