package ru.kpfu.itis.aygul.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by aygulmardanova on 06.05.16.
 */
@Aspect
@Component
public class MyLogger {

    private Logger log = Logger.getLogger(this.getClass());

    @Before("execution(* ru.kpfu.itis.aygul.controller.UserController.savePhoto(..))")
    public void log(JoinPoint point) {
        log.info("Admin's method " + point.getSignature().getName() + " is going to be called... Hello from Aspect!");
    }
}
