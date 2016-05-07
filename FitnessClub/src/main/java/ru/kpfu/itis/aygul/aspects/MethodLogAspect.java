package ru.kpfu.itis.aygul.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by aygulmardanova on 07.05.16.
 */
@Aspect
@Component
public class MethodLogAspect {

    private final static Logger logger = Logger.getLogger(MethodLogAspect.class);

    private Object logMethodInvocation(ProceedingJoinPoint jp) throws Throwable {
        StringBuilder info = new StringBuilder();
        long start = System.currentTimeMillis();
        Object result = jp.proceed();
        info.append(jp.getTarget().getClass().getSimpleName()).append(".")
                .append(jp.getSignature().getName())
                .append("(").append(Arrays.toString(jp.getArgs())).append(")")
                .append(" : ").append(result)
                .append(" in ").append(System.currentTimeMillis() - start).append(" msec.");
        logger.info(info);
        return result;
    }

    @Around("execution(* ru.kpfu.itis.aygul.repository.*.*(..))")
    public Object logRepositoryMethods(ProceedingJoinPoint jp) throws Throwable {
        return logMethodInvocation(jp);
    }

    @Around("execution(* ru.kpfu.itis.aygul.service.*.*(..))")
    public Object logService(ProceedingJoinPoint jp) throws Throwable {
        return logMethodInvocation(jp);
    }

}
