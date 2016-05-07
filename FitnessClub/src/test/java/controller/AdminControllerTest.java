package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;
import ru.kpfu.itis.aygul.controller.AdminController;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;
import ru.kpfu.itis.aygul.service.interfaces.SubscriptionService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;

import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 07.05.16.
 */


@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class AdminControllerTest {

    @InjectMocks
    private static AdminController adminController;

    @Mock
    private static ProbablyInstructorService probablyInstructorService;

    @Mock
    private static UserService userService;

    @Mock
    private static SubscriptionService subscriptionService;

    @Mock
    ModelMap model;

    @Before
    public void beforeMethod() throws IOException {
    }

    @Test
    public void getAdminProfileMethodShouldRedirectUser() throws IOException {
        Assert.assertEquals("redirect:user/profile", adminController.getAdminProfile(model));
    }

    @Test
    public void getRequestsMethodShouldReturnRequestsPage() throws IOException {
        Assert.assertEquals("requests", adminController.getRequests(model));
    }
}
