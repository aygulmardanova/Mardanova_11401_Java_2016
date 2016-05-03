package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by aygulmardanova on 30.04.16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    InstructorService instructorService;

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

        model = addLoginIntoModel(model);
        return model;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String returnProfilePage(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);

        User user = userService.getUserByLogin((String) model.get("login"));

        if (user.getRole().equals(Role.ROLE_INSTRUCTOR)) {
            Instructor instructor = instructorService.getByUser(user);
            if (instructor.getDescription() == null && instructor.getQualification() == null) {
                model.addAttribute("message", "Please, fill your instructor's profile.");
            }
            model.addAttribute("instructor", instructor);
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String returnSettingsPage(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);
        User user = userService.getUserByLogin((String) model.get("login"));
        model.addAttribute("user", user);
        return "settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String returnSuccessPage(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);
        return null;
    }
}
