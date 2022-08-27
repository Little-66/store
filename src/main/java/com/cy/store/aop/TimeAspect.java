package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect//当前类为切面类
public class TimeAspect {
    //环绕通知   参数就是目标方法
    @Around("execution(* com.cy.store.service.Impl.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行前
        long start = System.currentTimeMillis();
        Object proceed = point.proceed();//调用目标方法
        //执行后
        long end = System.currentTimeMillis();
        System.out.println("耗时="+(end-start));
        return proceed;
    }
}
