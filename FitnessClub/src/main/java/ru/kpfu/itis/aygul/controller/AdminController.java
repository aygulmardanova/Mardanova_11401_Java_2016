package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.service.ProbablyInstructorServiceImpl;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Айгуль on 24.04.2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProbablyInstructorService probablyInstructorService;

    @Autowired
    UserService userService;

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
    public String getAdminProfile(ModelMap model) throws IOException {
        //model = addLoginIntoModel(model);
        model = addMainPropsIntoModel(model);
        User user = userService.getUserByLogin((String) model.get("login"));
        model.addAttribute("user", user);
        return "redirect:user/profile";
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String getRequests(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);

        List<User> probablyInstructors;
        probablyInstructors = probablyInstructorService.getAllUsers();
        model.addAttribute("pi", probablyInstructors);
        return "requests";
    }

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public String acceptOrRejectInstr(ModelMap model, @RequestParam int user_id,
                                      @RequestParam String result) {
        if ("accept".equals(result)) {
            probablyInstructorService.acceptProbablyInstructor(user_id);
        } else {
            probablyInstructorService.rejectProbablyInstructor(user_id);
        }
        return "redirect:requests";
    }
}
