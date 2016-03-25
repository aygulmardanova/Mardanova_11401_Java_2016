package aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.ArrayList;

/**
 * Before and after generate() method appropriate messages will be printed.
 * AfterReturning aspect will check returning key-list, because it can't begin with 0.
 * Otherwise, it will substitute the returning list by another, where first and second values will be swapped
 */
@Aspect
public class LogGeneration {

    @Before("execution(* classes.Server.generate(..))")
    public void logBeforeGeneration() {
        System.out.println("Number is going to be generated");
    }

    @After("execution(* classes.Server.generate(..))")
    public void logAfterGeneration() {
        System.out.println("Number is generated");
    }

    @AfterReturning(pointcut = "execution(* classes.Server.generate())", returning = "key")
    public ArrayList<Integer> checkNumber(ArrayList<Integer> key) {
        if (key.get(0) == 0) {
            System.out.println("Number will be changed, because it can't begin with 0 " + key);
            int x = key.get(0);
            key.set(0, key.get(1));
            key.set(1, x);
        }
        return key;
    }

}
