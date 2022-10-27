package it.aop.aspects;

import it.aop.annotations.ToLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Order(2)
@Component
public class LoggingAspect {

    private Logger logger= Logger.getLogger(LoggingAspect.class.getName());

    //@Around("execution(* esercitazione.*.*(..))")
    @Around("@annotation(toLog)")
    public void log(ProceedingJoinPoint joinPoint, ToLog toLog) throws Throwable {
        Level level = Level.parse(toLog.level().toUpperCase());

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String[] parameterNames = methodSignature.getParameterNames();
        Class[] parameterTypes = methodSignature.getParameterTypes();
        Object[] parameterValues = joinPoint.getArgs();

        int count = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Method name:" + methodName +"()");
        while(count < parameterNames.length) {
            builder.append("\n [Parameter name:" + parameterNames[count] +
                    " - Parameter type:" + parameterTypes[count] +
                    " - Parameter value:" + parameterValues[count] +"]");
            count++;
        }
        logger.log(level, builder.toString());
        joinPoint.proceed();
    }
}
