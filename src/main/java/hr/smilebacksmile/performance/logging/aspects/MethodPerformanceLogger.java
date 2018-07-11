package hr.smilebacksmile.performance.logging.aspects;


import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;




@Aspect
public class MethodPerformanceLogger {

    private final static Logger LOGGER = LogManager.getLogger(MethodPerformanceLogger.class);

    @Around("execution(public * *(..)) && @annotation(hr.smilebacksmile.performance.logging.annotations.LoggablePerformance)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        StopWatch timer = new StopWatch();
        timer.start();
        Object result = point.proceed();
        timer.stop();

        LOGGER.trace("{}({}): {} in [{}]s",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                timer.getTime()
        );
        return result;
    }
}
