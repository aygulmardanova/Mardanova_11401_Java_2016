package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.aygul.service.RoleServiceImpl;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    RoleServiceImpl rs = new RoleServiceImpl();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {
        model.put("min_price", 6000);
        model.put("roles", rs.getRoles());
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String returnLogin() throws IOException {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProfile(ModelMap model) {
        String login = (String) model.get("login");
        String password = (String) model.get("password");
        return "profile";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    public String returnTrainersInfo(Model model){
        return "trainers";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String returnSignupPage() {
        return "signup";
    }
}
