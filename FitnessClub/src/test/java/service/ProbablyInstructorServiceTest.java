package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.ProbablyInstructorRepository;
import ru.kpfu.itis.aygul.service.ProbablyInstructorServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 13.05.16.
 */
public class ProbablyInstructorServiceTest {

    private static User user;

    private static ProbablyInstructor probablyInstructor;

    private static List<ProbablyInstructor> probablyInstructors;

    private static List<User> users;

    private static ProbablyInstructorServiceImpl probablyInstructorService;

    private static ProbablyInstructorRepository probablyInstructorRepository;



    @BeforeClass
    public static void beforeMethod() {
        user =  new User();
        user.setName("kolya");

        users = new ArrayList<>();
        users.add(user);

        probablyInstructor = new ProbablyInstructor();
        probablyInstructor.setUser(user);

        probablyInstructors = new ArrayList<>();
        probablyInstructors.add(probablyInstructor);

        probablyInstructorRepository = mock(ProbablyInstructorRepository.class);
        when(probablyInstructorRepository.findAll()).thenReturn(probablyInstructors);

        when(probablyInstructorRepository.findById(anyInt())).thenReturn(null);
        when(probablyInstructorRepository.findById(probablyInstructor.getId())).thenReturn(probablyInstructor);

        when(probablyInstructorRepository.findOneByUser(any(User.class))).thenReturn(null);
        when(probablyInstructorRepository.findOneByUser(user)).thenReturn(probablyInstructor);

        probablyInstructorService = new ProbablyInstructorServiceImpl();
        probablyInstructorService.setProbablyInstructorRepository(probablyInstructorRepository);
    }

    @Test
    public void getAllMethodShouldRetrnCorrectList() {
        Assert.assertEquals(probablyInstructors, probablyInstructorService.getAll());
    }

    @Test
    public void isProbablyInstructorShouldReturnTrueIfUserIsProbInstr() {
        Assert.assertTrue(probablyInstructorService.isProbablyInstructor(user));
    }

    @Test
    public void isProbablyInstructorShouldReturnFalseIfUserIsNotProbInstr() {
        Assert.assertFalse(probablyInstructorService.isProbablyInstructor(new User()));
    }

    @Test
    public void getAllUsersShouldReturnCorrectListOfUsers() {
        Assert.assertEquals(users, probablyInstructorService.getAllUsers());
    }


}
