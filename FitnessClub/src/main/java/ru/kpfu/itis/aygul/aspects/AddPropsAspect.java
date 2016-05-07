package ru.kpfu.itis.aygul.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.Properties;

/**
 * Created by aygulmardanova on 07.05.16.
 */
@Aspect
@Component
public class AddPropsAspect {

    private static Properties props = new Properties();

    @Around("execution(* ru.kpfu.itis.aygul.controller.*.*(..))")
    public Object logService(ProceedingJoinPoint jp) throws Throwable {

        props.load(getClass().getResourceAsStream("/clubinfo.properties"));

        if (jp.getArgs().length>0) {
            ModelMap model = (ModelMap) jp.getArgs()[0];
            model.addAttribute("clubname", props.getProperty("club.name"));
            model.addAttribute("slogan", props.getProperty("club.slogan"));
            model.addAttribute("phone_number", props.getProperty("club.phone_number"));
            jp.getArgs()[0] = model;
        }

        return jp.proceed();
    }

}
