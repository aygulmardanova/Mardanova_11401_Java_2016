package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.ClassService;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.SubscriptionService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ClassService classService;

    private static Properties props = new Properties();

    private ModelMap addLoginIntoModel(ModelMap model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if (login != null && !login.equals("")) {
            model.addAttribute("login", login);
            System.out.println("Authenticated user's login: = " + login);
        }

        return model;
    }

    private ModelMap addMainPropsIntoModel(ModelMap model) throws IOException {
        props.load(getClass().getResourceAsStream("/clubinfo.properties"));
        model.addAttribute("clubname", props.getProperty("club.name"));
        model.addAttribute("phone_number", props.getProperty("club.phone_number"));

        return model;
    }

    private ModelMap addAllPropsIntoModel(ModelMap model) throws IOException {
        props.load(getClass().getResourceAsStream("/clubinfo.properties"));
//        model.addAttribute("clubname", props.getProperty("club.name"));
//        model.addAttribute("phone_number", props.getProperty("club.phone_number"));
        model = addMainPropsIntoModel(model);
        model.addAttribute("description", props.getProperty("club.description"));
        model.addAttribute("address", props.getProperty("club.address"));
        model.addAttribute("email", props.getProperty("club.email"));
        model.addAttribute("work_week_open", props.getProperty("club.work_week_open"));
        model.addAttribute("work_week_close", props.getProperty("club.work_week_close"));
        model.addAttribute("weekend_open", props.getProperty("club.weekend_open"));
        model.addAttribute("weekend_close", props.getProperty("club.weekend_close"));
        model.addAttribute("class_duration", props.getProperty("club.class_duration"));

        return model;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {

        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        int min_price = subscriptionService.findMinPrice();
        model.put("min_price", min_price);
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String returnLogin(ModelMap model,
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "message", required = false) String message) throws IOException {

        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        if ("true".equals(error)) {
            model.addAttribute("error_msg", "Wrong login or password");
            return "login";
        }
        String message1 = (String) model.get("message");
        if (message != null) {
            model.addAttribute("message", message1);
            return "login";
        }

        return "login";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String returnUsersPage(ModelMap model) throws IOException {
        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    public String returnTrainersInfo(ModelMap model) throws IOException {
        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        List<Instructor> instructors = instructorService.getAll();
        model.addAttribute("instructors", instructors);
        return "trainers";
    }

    @RequestMapping(value = "/trainer/${id}", method = RequestMethod.GET)
    public String returnTrainerPage(ModelMap model, @PathVariable String id) throws IOException {
        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        System.out.println("//////////////////id = " + id);
        int id1 = Integer.parseInt(id);
        System.out.println("Instructor by id: " + id);
        Instructor instructor = instructorService.getById(id1);
        model.addAttribute("instructor", instructor);
        return "trainer";
    }

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public String returnClassesPage(ModelMap model) throws IOException {
        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        List<ClassEntity> classes;
        classes = classService.getAll();
        model.addAttribute("classes", classes);

        return "classes";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String returnSignupPage(ModelMap model) throws IOException {
        String message = (String) model.get("message");
        if (message != null) {
            model.addAttribute("message", model.get("message"));
        }
        model = addMainPropsIntoModel(model);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(ModelMap model, @RequestParam String login, @RequestParam String password,
                               @RequestParam String password_repeat, @RequestParam String name,
                               @RequestParam String surname, @RequestParam String email,
                               @RequestParam String phone, @RequestParam(required = false) boolean trainer) throws IOException {

        model = addMainPropsIntoModel(model);
        if (password.equals(password_repeat)) {
            Role role;
            if (trainer) {
                role = Role.ROLE_INSTRUCTOR;
                System.out.println("I want to be a trainer " + login);
            } else {
                role = Role.ROLE_USER;
            }
            userService.saveUser(login, password, email, name, surname, null, phone, role);
            model.addAttribute("message",
                    "You have just registered successfully. Please enter your data once again");
            return "login";
        } else {
            model.addAttribute("message", "Your passwords have different values");
            return "signup";
        }
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public String returnPricesPage(ModelMap model) throws IOException {
        model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);

        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "prices";
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String returnAboutClubPage(ModelMap model) throws IOException {
        model = addLoginIntoModel(model);
        model = addAllPropsIntoModel(model);

        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "clubinfo";
    }


}
