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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aygulmardanova on 08.05.16.
 */
@Controller
@RequestMapping(value = "signup")
public class SignupController {


    public static final Pattern login_p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$");
    public static final Pattern pass_p = Pattern.compile("^[a-zA-Z0-9]{5,20}$");
    public static final Pattern email_p = Pattern.compile("^([a-z0-9_\\.-])+@[a-z0-9-]+\\.([a-z]{2,4}\\.)?[a-z]{2,4}$");
    public static final Pattern phone_p = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");

    public static boolean checkWithRegExp(String check, String type) {
        Matcher m;
        if (check == null || "".equals(check)) {
            return true;
        }
        switch (type) {
            case "login": m = login_p.matcher(check);
                break;
            case "pass": m = pass_p.matcher(check);
                break;
            case "email": m = email_p.matcher(check);
                break;
            case "phone": m = phone_p.matcher(check);
                break;
            default: return false;
        }
        return m.matches();
    }

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

        if (userService.getUserByLogin(login) != null) {
            model.addAttribute("message", "Login " + login + " is busy");
            return "signup";
        }
        if (userService.getUserByEmail(email) != null) {
            model.addAttribute("message", "Email " + email + " is busy");
            return "signup";
        }
        if (!checkWithRegExp(login, "login")) {
            model.addAttribute("message", "Login " + login + " is incorrect");
            return "signup";
        }
        if (!checkWithRegExp(password, "pass")) {
            model.addAttribute("message", "Incorrect password format: " + password);
            return "signup";
        }
        if (!checkWithRegExp(email, "email")) {
            model.addAttribute("message", "Email " + email + " is incorrect");
            return "signup";
        }
        if (!checkWithRegExp(phone, "phone")) {
            model.addAttribute("message", "Phone " + phone + " is incorrect");
            return "signup";
        }
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
