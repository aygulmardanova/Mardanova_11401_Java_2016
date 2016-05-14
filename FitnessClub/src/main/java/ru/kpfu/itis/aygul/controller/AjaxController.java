package ru.kpfu.itis.aygul.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.aygul.aspects.annotations.AuthUserName;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.json.JSONObject;


/**
 * Created by aygulmardanova on 07.05.16.
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    InstructorService instructorService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public @ResponseBody String returnIfLoginExists(ModelMap model, @RequestParam(value = "login") String login) {
        User user = userService.getUserByLogin(login);
        if (user != null) {
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public @ResponseBody String returnIfEmailExists(ModelMap model, @RequestParam(value = "email") String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public String returnSecondPage(ModelMap model) throws IOException {
        System.out.println("Get method is called");

        return "ajax_trainers";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public @ResponseBody List<User> returnSecondResultPage(ModelMap model, @RequestParam String sort) throws IOException {
        System.out.println("Method post for this url is working");

        List<User> users = userService.getAllInstructorsSortBy(sort);

        System.out.println("Got sort parameter: " + sort);
        System.out.println("Users: " + users);

        return users;
    }

}
