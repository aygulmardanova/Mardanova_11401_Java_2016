import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.kpfu.itis.aygul.model.Role;
import ru.kpfu.itis.aygul.repository.RoleRepository;

import java.util.List;

public class Main {

    @Autowired
    static RoleRepository rr;

    public static void main(String[] args) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("persistence-config.xml");
//        RoleRepository rr = ac.getBean(RoleRepository.class);
        System.out.println(rr);
        System.out.println("Searching by id = 1: " + rr.findById(1));
        System.out.println("---");
        System.out.println("Searching by role = admin: " + rr.findByRole("admin"));
        System.out.println("---");
        System.out.println("Find all method: ");
        List<Role> roles = rr.findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}