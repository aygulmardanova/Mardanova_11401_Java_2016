package task013;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * If setEmail() method gets the incorrect email address, method won't work.
 *
 * Task013
 * Mardanova Aygul
 * 11-401
 */

@Aspect
public class CheckEmail {

    private static final String EMAIL_PATTERN = "^[A-Za-z][_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$";

    private boolean correctEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Around("execution(public void *..setEmail(String))")
    public Object checkEmail(ProceedingJoinPoint jp) throws Throwable {
        String email = (String) jp.getArgs()[0];
        if (correctEmail(email)) {
            return jp.proceed();
        } else
            System.out.println("        Incorrect email address: " + email);
        return null;
    }
}
