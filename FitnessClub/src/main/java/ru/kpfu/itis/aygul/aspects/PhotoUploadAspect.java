package ru.kpfu.itis.aygul.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by aygulmardanova on 07.05.16.
 */
@Aspect
@Component
public class PhotoUploadAspect {

    private Logger log = Logger.getLogger(this.getClass());

    @Pointcut("execution(static * ru.kpfu.itis.aygul.controller.UserController.savePhoto(..))")
    public void someMethodInvoke() { }


    @Before(value = "someMethodInvoke()")
    public void logUploadingBefore(JoinPoint point) {
        MultipartFile photo = (MultipartFile) point.getArgs()[0];
        String name = photo.getOriginalFilename();
        log.info("You are going to upload photo with name : " + name + ". Hello from Aspect!");
    }

//    @Before("execution(static * ru.kpfu.itis.aygul.controller.UserController.savePhoto(..))")
//    public void logUploadingBefore(JoinPoint point) {
//        MultipartFile photo = (MultipartFile) point.getArgs()[0];
//        String name = photo.getOriginalFilename();
//        log.info("You are going to upload photo with name : " + name + ". Hello from Aspect!");
//    }

}
