package ru.kpfu.itis.aygul.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by aygulmardanova on 06.05.16.
 */
@Aspect
@Component
public class MyLogger {

    private Logger log = Logger.getLogger(MyLogger.class);

    @Before("execution(static * ru.kpfu.itis.aygul.controller.UserController.savePhoto(..))")
    public void log(JoinPoint point) {
        MultipartFile file = (MultipartFile) point.getArgs()[0];
        log.info("User is going to save a photo with name " + file.getOriginalFilename() + ". Hello from Mylogger aspect!");
    }

//    @AfterReturning(pointcut = "execution(* ru.kpfu.itis.aygul.controller.UserController.savePhoto(..))",
//     returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("Log after returning method is working!");
//        log.info("Saved photo's name is " + result);
//        log.info("////////////////////////");
//    }

}
