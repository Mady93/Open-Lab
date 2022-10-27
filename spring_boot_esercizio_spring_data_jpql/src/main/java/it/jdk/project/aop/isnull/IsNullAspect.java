package it.jdk.project.aop.isnull;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import it.jdk.project.aop.isnull.isNull;

@Aspect
@Order(1)
@Component
public class IsNullAspect {

	//Logger.log(level.WARNING+"") --- mostra a video a livelli
	private Logger logger= Logger.getLogger(LoggingAspect.class.getName());

    @Around("@annotation(isNull)")
    public void log(ProceedingJoinPoint joinPoint, isNull isNull) throws Throwable {
    	// la signature del metodo è come è strutturatto l'oggetto
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String  methodName= methodSignature.getMethod().getName();
        Object[] parameterNamesValue = joinPoint.getArgs();
        
        boolean isPresent = false;
        
        for(int i=0; i<parameterNamesValue.length; i++) {
            System.out.println("parameterNamesValue[i]" +parameterNamesValue[i]);
        	 if(parameterNamesValue[i] == null || (parameterNamesValue[i] instanceof String && !((String)parameterNamesValue[i]).isEmpty() )) {
        		 isPresent = true;
     		       break;
        	 }
        }
        if(isPresent == true) {
       	 throw new Exception("IllegalArgumentException");
        }
        else {
        	joinPoint.proceed();
        	this.logger.info("I parametri erano tutti presenti");
        }
       
}
}
