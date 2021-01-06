package com.example.demo.monitor;

import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP测试
 */

@CommonsLog
@Aspect
@Component
public class TestMonitor {


    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    private void controllerLayer() {
    }

    /**
     * Monitor the elapsed time of method on controller layer, in
     * order to detect performance problems as soon as possible.
     * If elapsed time > 1 s, log it as a warn. Otherwise, log it
     * as an info.
     */
    @Around("controllerLayer()")
    public Object monitorElapsedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("开始+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Object object = proceedingJoinPoint.proceed();
        log.info("结束+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return object;
    }


}
