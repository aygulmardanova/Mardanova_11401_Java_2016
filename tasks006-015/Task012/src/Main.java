import database.User;
import database.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Task012
 * Mardanova Aygul
 * 11-401
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
        UserRepository ur = (UserRepository) ac.getBean("userrepository");
        User aig = ur.getUserByLogin("aig WHERE");
        if (aig != null) {
            System.out.println(aig.getLogin() + " - " + aig.getNumber());
        }
        ur.addUser("adilya", "adilya", "123456789 where");
    }
}
