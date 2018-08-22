package com.neilren.neilren4j.common.aspect;

import com.neilren.neilren4j.entity.Footer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName PerformanceAspect
 * @Description TODO
 * @Date 2018/8/1 15:36
 */
@Aspect
@Slf4j
@Component
public class PerformanceAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();// 开始时间
    ThreadLocal<Long> calls = new ThreadLocal<>();//方法调用次数

    @Pointcut("execution(* com.neilren.neilren4j.*..*(..))")
    public void logPointcut() {
    }

    /**
     * 记录返回值
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "logPointcut()")
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        if (obj != null && obj.getClass().equals(ModelAndView.class)) {
            ModelAndView mv = (ModelAndView) obj;
            mv.addObject("calls", calls.get());
            Object foot = mv.getModelMap().get("footer");
            DecimalFormat df = new DecimalFormat("######0.000000");
            if (foot != null && Footer.class.equals(foot.getClass())) {
                Footer footer = (Footer) foot;
                footer.setCalls(calls.get().toString());
                Long time = System.nanoTime() - startTime.get();
                double timed = time / 1000000000D;
                footer.setProcessed(df.format(timed));
                mv.addObject("footer", footer);
            } else {
                Footer footer = new Footer();
                footer.setCalls(calls.get().toString());
                Long time = System.nanoTime() - startTime.get();
                double timed = time / 1000000000D;
                footer.setProcessed(df.format(timed));
                mv.addObject("footer", footer);
            }
        }
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(System.nanoTime());
        calls.set(0L);
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
            String MethodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();// 参数
            int argsSize = args.length;
            String argsTypes = "";
            String typeStr = joinPoint.getSignature().getDeclaringType().toString().split(" ")[0];
            String returnType = joinPoint.getSignature().toString().split(" ")[0];
            StringBuffer sb = new StringBuffer();
            sb.append("类/接口:" + tragetClassName + "(" + typeStr + ")\n");
            sb.append("方法:" + MethodName + "\n");
            sb.append("参数个数:" + argsSize + "\n");
            sb.append("返回类型:" + returnType + "\n");
            if (argsSize > 0) {
                // 拿到参数的类型
                for (Object object : args) {
                    if (object != null)
                        argsTypes += object.getClass().getTypeName().toString() + " ";
                }
                sb.append("参数类型：" + argsTypes + "\n");
            }
            calls.set(calls.get() + 1);
            Long total = end - start;
            if (total > 1000) {
                log.warn("\nWaring:方法执行时间过长！耗时：" + total + " ms.\n" + sb.toString(), result);
            }
            return result;
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
