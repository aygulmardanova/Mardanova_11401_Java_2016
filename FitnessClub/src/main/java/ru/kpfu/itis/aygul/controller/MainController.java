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
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.SubscriptionService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;
import java.util.List;

//@PropertySource("classpath:clubinfo.properties")
@Controller
public class MainController {

    @Value("${club.name}")
    String name;

    @Autowired
    UserService userService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if (login != null && !login.equals("")) {
            model.addAttribute("login", login);
            System.out.println("Authenticated user's login: = " + login);
        }

        int min_price = subscriptionService.findMinPrice();
        model.put("min_price", min_price);
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String returnLogin(ModelMap model) throws IOException {

        if (model.get("message") != null) {
            model.put("message", model.get("message"));
        }

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProfile(ModelMap model,
                               @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error_msg", "Wrong login or password");
        }
        return "profile";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    public String returnTrainersInfo(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("login", login);
        System.out.println("Authenticated user's login: = " + login);

        List<Instructor> instructors = instructorService.getAll();
        //List<Instructor> instructors = new ArrayList<Instructor>();
        model.addAttribute("instructors", instructors);
        return "trainers";
    }

    @RequestMapping(value = "/trainer/${id}", method = RequestMethod.GET)
    public String returnTrainerPage(Model model, @PathVariable int id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("login", login);
        System.out.println("Authenticated user's login: = " + login);

        Instructor instructor = instructorService.getById(id);
        model.addAttribute("instructor", instructor);
        return "trainer";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String returnSignupPage() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(ModelMap model, @RequestParam String login, @RequestParam String password,
                               @RequestParam String password_repeat, @RequestParam String name,
                               @RequestParam String surname, @RequestParam String email,
                               @RequestParam String phone, @RequestParam(required = false) boolean trainer) {

        if (password.equals(password_repeat)) {
            Role role;
            if (trainer) {
                role = Role.ROLE_INSTRUCTOR;
            } else {
                role = Role.ROLE_USER;
            }
            userService.saveUser(login, password, email, name, surname, null, phone, role);
            model.addAttribute("message",
                    "You have just registered successfully. Please enter your data once again");
            return "redirect:login";
        } else {

            return "signup";
        }
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public String returnPricesPage(ModelMap model) {
        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "prices";
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String returnAboutClubPage(ModelMap model) {

        //String name = "${club.name}";
        model.put("name", name);
        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "clubinfo";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String returnSettingsPage(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("login", login);
        System.out.println("Authenticated user's login: = " + login);

        return "settings";
    }

}
