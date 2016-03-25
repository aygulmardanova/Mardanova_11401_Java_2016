package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import java.util.ArrayList;

/**
 * This aspect checks go() method in Server class. It prints "Starting" before it's proceeding
 * and prints generated number (key) after it's proceeding. generate() method will be called before go() method,
 * so here generated key-list will be checked, because it mustn't begin with 0.
 * In this case the server will start one more time. After method will be done, the aspect will print the generated
 * number, which is going to be used in game.
 *
 * P.S.: checking the fact, that generated number won't begin with 0, is redundant here, because it will be checked
 * and changed in appropriate aspect for generate() method. We can use one of this two aspects.
 */
@Aspect
public class LogGoServer {

    @Around("execution(* classes.Server.go(..))")
    public Object goLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Starting");
        ArrayList<Integer> key = (ArrayList<Integer>) jp.getArgs()[0];
        if (key.get(0) == 0) {
            System.out.println("Number can't begin with 0: " + key);
            System.out.println("----------");
            return null;
        }

        jp.proceed();

        for (int i = 0; i < key.size(); i++) {
            System.out.print(key.get(i));
        }
        System.out.println();
        System.out.println("----------");
        return null;
    }

}
