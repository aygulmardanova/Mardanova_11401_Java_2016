package ru.kpfu.itis.aygul.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by aygulmardanova on 06.05.16.
 *
 * These annotation is used to mark the methods,
 * where authenticated user's login should be printed
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthUserName {
}
