package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    InstructorService instructorService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {
        model.put("min_price", 6000);
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
    public String returnTrainersInfo(Model model) {
        List<Instructor> instructors = instructorService.getAll();
        //List<Instructor> instructors = new ArrayList<Instructor>();
        model.addAttribute("instructors", instructors);
        return "trainers";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String returnSignupPage() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(@RequestParam String login, @RequestParam String password,
                               @RequestParam String password_repeat, @RequestParam String name,
                               @RequestParam String surname, @RequestParam String email,
                               @RequestParam String phone, @RequestParam(required = false) boolean trainer) {

        if (password.equals(password_repeat)) {
            Role role;
            if (trainer) {
                role = Role.ROLE_INSTRUCTOR;
            }
            else {
                role = Role.ROLE_USER;
            }
            userService.saveUser(login, password, email, name, surname, null, phone, role);
            return "redirect:main";
        } else
            return "signup";
    }

}
