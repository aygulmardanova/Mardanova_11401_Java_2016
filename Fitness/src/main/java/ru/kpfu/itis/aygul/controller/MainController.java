package ru.kpfu.itis.aygul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping()
public class MainController {

    //UserService us = new UserService();

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {
        model.put("min_price", 6000);
        return "main";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String returnLogin() throws IOException {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginProfile(ModelMap model) {
        String login = (String) model.get("login");
        String password = (String) model.get("password");
        return "profile";
    }
}
