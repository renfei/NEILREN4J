package com.neilren.neilren4j.common.aspect;

import com.neilren.neilren4j.entity.LogAccess;
import com.neilren.neilren4j.service.LogService;
import com.neilren.neilren4j.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName HttpAspect
 * @Description TODO
 * @Date 2018/8/1 11:17
 */
@Aspect
@Slf4j
@Component
public class ControllerAspect {
    @Autowired
    private LogService logService;
    @Autowired
    private SecurityService securityService;

    @Pointcut("execution(public * com.neilren.neilren4j.controller.*.*(..))")
    public void controller() {
    }

    /**
     * 记录Access访问日志
     *
     * @param joinPoint
     */
    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LogAccess logAccess = new LogAccess(request);
        logService.writeAccessLog(logAccess);
    }

    @Around("controller()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
//        Object[] args = pjp.getArgs();
//        HttpServletRequest request = null;
//        for (Object arg : args) {
//            //logger.debug("arg: {}", arg);
//            if (arg instanceof Map<?, ?>) {
//                //提取方法中的MAP参数，用于记录进日志中
//                @SuppressWarnings("unchecked")
//                Map<String, Object> map = (Map<String, Object>) arg;
//            } else if (arg instanceof HttpServletRequest) {
//                request = (HttpServletRequest) arg;
//            } else if (arg instanceof HttpServletResponse) {
//                //do nothing...
//            } else {
//                //allParams.add(arg);
//            }
//        }
//        if (!securityService.checkRequest(args)) {
//            HttpHeaders headers = new HttpHeaders();
//            throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
//        }
        result = pjp.proceed();
        return result;
    }

    /**
     * 记录返回值
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "controller()")
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
    }

}
