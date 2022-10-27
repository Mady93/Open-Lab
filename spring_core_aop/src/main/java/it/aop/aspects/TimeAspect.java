package it.aop.aspects;

import it.aop.annotations.LogTime;
import it.aop.annotations.ToLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Order(3)
@Component
public class TimeAspect {

    private Logger logger= Logger.getLogger(TimeAspect.class.getName());

    //@Around("execution(* esercitazione.*.*(..))")
    @Around("@annotation(logTime)")
    public void log(ProceedingJoinPoint joinPoint, LogTime logTime) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        logger.log(Level.INFO, "Method "+methodName+"()" + " completed in " + (end-start) + " ms");
    }
}
