package ru.kpfu.itis.aygul.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import ru.kpfu.itis.aygul.controller.MainController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aygulmardanova on 06.05.16.
 */
@Aspect
@Component
public class MyAspect {

    private Logger log = Logger.getLogger(this.getClass());


    @Pointcut("@annotation(ru.kpfu.itis.aygul.aspects.annotations.AuthUserName)")
    public void logAuthUserName() {
    }

    @AfterReturning(pointcut ="execution(* ru.kpfu.itis.aygul.controller.*.addLoginIntoModel(..))", returning="result")
    public void logLogin(JoinPoint point, ModelMap result) {

        log.info(result.get("login") + ". Hello from Aspect!");
    }


}
