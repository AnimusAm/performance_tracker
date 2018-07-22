package hr.smilebacksmile.performance.logging.aspects;


import hr.smilebacksmile.performance.logging.Test;
import hr.smilebacksmile.performance.logging.annotations.LoggablePerformance;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;


@Aspect
public class MethodPerformanceLogger {

    private Logger getLogger(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LoggablePerformance loggableAnnotation= signature.getMethod().getDeclaredAnnotation(LoggablePerformance.class);
        return LoggerFactory.getLogger(loggableAnnotation.logger());
    }

    @Around("execution(public * *..*(..)) && @annotation(hr.smilebacksmile.performance.logging.annotations.LoggablePerformance)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.nanoTime();
        Object result = point.proceed();
        long stopTime = System.nanoTime();


        getLogger(point).trace("{}({}): {} in [{}]ns",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                stopTime - startTime
        );
        return result;
    }
}
