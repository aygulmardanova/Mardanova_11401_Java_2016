package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;

/**
 * Created by aygulmardanova on 08.05.16.
 */
@Controller
@RequestMapping(value = "signup")
public class SignupController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String returnSignupPage(ModelMap model) throws IOException {
        String message = (String) model.get("message");
        if (message != null) {
            model.addAttribute("message", model.get("message"));
        }

        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(ModelMap model, @RequestParam String login, @RequestParam String password,
                               @RequestParam String password_repeat, @RequestParam String name,
                               @RequestParam String surname, @RequestParam String email,
                               @RequestParam String phone, @RequestParam(required = false) boolean trainer) throws IOException {

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
            model.addAttribute("message", "Repeated password has different value");
            return "signup";
        }
    }

}
