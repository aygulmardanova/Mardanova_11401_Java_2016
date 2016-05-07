package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.repository.InstructorRepository;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.UserServiceImpl;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 07.05.16.
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ProbablyInstructorService probablyInstructorService;

    @Mock
    InstructorRepository instructorRepository;

    private static User user;

    private static ProbablyInstructor probablyInstructor;

    private static List<User> users;

    @BeforeClass
    public static void beforeClassMethod() {
        user = new User();
        user.setLogin("kolya");
        user.setPassword("111");
        user.setPhoto("photo.jpg");
        user.setPhoneNumber("12345678987");
        user.setEmail("kolya@mail.ru");
        user.setRole(Role.ROLE_USER);
        user.setName("Nikolay");
        user.setSurname("Ivanov");

        users = new ArrayList<>();
        users.add(user);

    }

    @Test
    public void checkUserShouldReturnBooleanIfCorrectUser() {
        when(userService.getUserByLogin("kolya")).thenReturn(user);

        User user1 = userService.getUserByLogin("kolya");
        Assert.assertEquals(user.getLogin(), user1.getLogin());
        Assert.assertEquals(user.getPassword(), user1.getPassword());
    }

    @Ignore
    @Test
    public void updateUserMethodShouldSetNewValueForFields() {

        String newPhoto = null;
        String newLogin = "kolya";
        String newName = "Nikolya";
        String newSurn = "Ivanov";
        String newEmail = "iv@mail.ru";
        String newPas = "1234";
        String newPasRep = "1234";
        String newPhone = "12345678234";

        userService.updateUser(user.getId(), newPhoto, newLogin, newName, newSurn, newEmail, newPas, newPasRep, newPhone);

        User user1 = userService.getUserById(user.getId());
        Assert.assertEquals(newLogin, user1.getLogin());
        Assert.assertEquals(newName, user1.getName());
        Assert.assertEquals(newSurn, user1.getSurname());
        Assert.assertEquals(newEmail, user1.getEmail());
        Assert.assertEquals(newPas, user1.getPassword());
        Assert.assertEquals(newPhone, user1.getPhoneNumber());
    }


}
