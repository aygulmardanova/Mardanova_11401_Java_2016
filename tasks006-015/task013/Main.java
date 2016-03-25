package task013;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Task013
 * Mardanova Aygul
 * 11-401
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("task013/spring-config13.xml");
        User ivan = (User) ac.getBean("ivan");
        ivan.setEmail("ivan@gmail.com");
        ivan.setEmail("sss");
        ivan.setEmail("ivan123@mail.ru");
        ivan.setEmail("1ivan123@mail.ru");
        ivan.setEmail("ivan123@mail.r");
    }
}
