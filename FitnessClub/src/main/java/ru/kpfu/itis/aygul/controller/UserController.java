package ru.kpfu.itis.aygul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aygulmardanova on 30.04.16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private ModelMap addLoginIntoModel(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("login", login);
        System.out.println("Authenticated user's login: = " + login);

        return model;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String returnProfilePage(ModelMap model) {
        model = addLoginIntoModel(model);
        return "profile";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String returnSettingsPage(ModelMap model) {
        model = addLoginIntoModel(model);
        return "settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String returnSuccessPage(ModelMap model) {
        model = addLoginIntoModel(model);
        return null;
    }
}
