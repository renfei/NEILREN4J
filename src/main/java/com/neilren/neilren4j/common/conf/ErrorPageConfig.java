package com.neilren.neilren4j.common.conf;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName ErrorPageConfig
 * @Description TODO
 * @Date 2018/7/27 11:25
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage e401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
        ErrorPage e403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage e405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
        errorPageRegistry.addErrorPages(e401, e403, e404, e405, e500);
    }
}
