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

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 07.05.16.
 */

//@RunWith(MockitoJUnitRunner.class)
//@WebAppConfiguration
public class UserServiceTest {

//    @InjectMocks
//    UserServiceImpl userService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    ProbablyInstructorService probablyInstructorService;
//
//    @Mock
//    InstructorRepository instructorRepository;

    private static User user;

    private static List<User> users;

    private static UserServiceImpl userService;

    private static UserRepository userRepository;

    private static final String incor_pass = "0000";
    private static final String incor_login = "Aaaaa";
    private static final String incor_email = "mail@.mail.ru";

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

        userService = new UserServiceImpl();

        userRepository = mock(UserRepository.class);

        when(userRepository.findById(anyInt())).thenReturn(null);
        when(userRepository.findById(user.getId())).thenReturn(user);

        when(userRepository.findByLogin(anyString())).thenReturn(null);
        when(userRepository.findByLogin(user.getLogin())).thenReturn(user);

        when(userRepository.findOneByEmail(anyString())).thenReturn(null);
        when(userRepository.findOneByEmail(user.getEmail())).thenReturn(user);

        when(userRepository.findAll()).thenReturn(users);

//        when(userRepository.save(user)).thenReturn(user);

        when(userRepository.findAllByRoleOrderByNameAsc(any(Role.class))).thenReturn(users);
        when(userRepository.findAllByRoleOrderByNameDesc(any(Role.class))).thenReturn(users);
        when(userRepository.findAllByRoleOrderBySurnameAsc(any(Role.class))).thenReturn(users);
        when(userRepository.findAllByRoleOrderBySurnameDesc(any(Role.class))).thenReturn(users);

        userService.setUserRepository(userRepository);

    }

    @Test
    public void getAllUsersShouldReturnCorrectList() {
        Assert.assertEquals(users, userService.getAllUsers());
    }

    @Test
    public void getUserByLoginShouldReturnCorrectUser() {
        Assert.assertEquals(user, userService.getUserByLogin(user.getLogin()));
    }

    @Test
    public void getUserByLoginShouldReturnNullIfIncorLogin() {
        Assert.assertNull(userService.getUserByLogin(incor_login));
    }

    @Test
    public void getUserByEmailShouldReturnCorrectUser() {
        Assert.assertEquals(user, userService.getUserByEmail(user.getEmail()));
    }

    @Test
    public void getUserByEmailShouldReturnNullIfIncorEmail() {
        Assert.assertNull(userService.getUserByEmail(incor_email));
    }

    @Test
    public void checkUserByLoginShouldReturnTrueIfCorrectUser() {
        Assert.assertTrue(userService.checkUser(user.getLogin(), user.getPassword()));
    }

    @Test
    public void checkUserByLoginShouldReturnFalseIfIncorrectPassword() {
        Assert.assertFalse(userService.checkUser(user.getLogin(), incor_pass));
    }

    @Test
    public void checkUserByIdShouldReturnTrueIfCorrectUser() {
        Assert.assertTrue(userService.checkUser(user.getId(), user.getPassword()));
    }

    @Test
    public void checkUserByIdShouldReturnFalseIfIncorrectPassword() {
        Assert.assertFalse(userService.checkUser(user.getId(), incor_pass));
    }

    @Test
    public void getAllInstructorsSortBySortByAllTypesShouldReturnCorrectList() {
        Assert.assertEquals(users, userService.getAllInstructorsSortBy("name,asc"));
        Assert.assertEquals(users, userService.getAllInstructorsSortBy("name,desc"));
        Assert.assertEquals(users, userService.getAllInstructorsSortBy("surn,asc"));
        Assert.assertEquals(users, userService.getAllInstructorsSortBy("surn,desc"));
    }

    @Test
    public void getAllInstructorsSortByShouldReturnNullIfIncorrectSortType() {
        Assert.assertNull(userService.getAllInstructorsSortBy(incor_login));
    }
}
