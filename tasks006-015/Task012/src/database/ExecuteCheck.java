package database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Check each method, that is going to do some calls to the database. Methods are from UserRepository class
 *
 * Task012
 * Mardanova Aygul
 * 11-401
 */

@Aspect
public class ExecuteCheck {

    private String[] sqlExpr = new String[]{"INSERT", "DELETE", "SELECT", "UPDATE", "ALTER", "AND", "WHERE"};

    @Around("execution(* database.UserRepository.*(..))")
    public Object checkInjection(ProceedingJoinPoint jp) throws Throwable {

        int count = jp.getArgs().length;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < sqlExpr.length; j++) {
                if (((String) jp.getArgs()[i]).toUpperCase().contains(sqlExpr[j])) {
                    System.out.println("There are some type of sql-injection: " + jp.getArgs()[i]);
                    if (jp.getSignature().getName().equals("checkPassword")
                            || jp.getSignature().getName().equals("addUser")) {
                        return false;
                    }
                    return null;
                }
            }
        }
        System.out.println("String parameters are OK. Method will be done");
        return jp.proceed();
    }
}
