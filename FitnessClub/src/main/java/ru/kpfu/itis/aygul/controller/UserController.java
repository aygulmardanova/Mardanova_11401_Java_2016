package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        if (model.get("success") != null) {
            model.addAttribute("success", model.get("success"));
        }

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
        if (model.get("message") != null) {
            model.addAttribute("message", model.get("message"));
        }
        User user = userService.getUserByLogin((String) model.get("login"));
        model.addAttribute("user", user);
        return "settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String returnSettingsPagePost(ModelMap model,
                                    @RequestParam(value = "photo", required = false) MultipartFile photo,
                                    @RequestParam(value = "login", required = false) String login,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "surname", required = false) String surname,
                                    @RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "old_password", required = false) String old_password,
                                    @RequestParam(value = "new_password", required = false) String new_password,
                                    @RequestParam(value = "new_password_repeat", required = false) String new_password_repeat,
                                    @RequestParam(value = "phone", required = false) String phone,
                                    @RequestParam("user_id") int id) throws IOException {
        model = addMainPropsIntoModel(model);
        String photoName = "";

        if (!photo.isEmpty()) {
            byte[] bytes = photo.getBytes();
            // store the bytes somewhere
            return "redirect:uploadSuccess";
        }

        if (new_password != null && !new_password.equals("")) {
            if ((old_password == null) ||
                    (!old_password.equals("") && !userService.checkUser(id, old_password))) {
                model.addAttribute("message", "You entered incorrect password");
                return "redirect:settings";
            }
        }
        userService.updateUser(id, photoName, login, name, surname, email,
                new_password, new_password_repeat, phone);
        model.addAttribute("success", "Your profile has been changed successfully");
        return "redirect:profile";
    }

    @RequestMapping(value = "/instr-settings", method = RequestMethod.GET)
    public String returnInstructorSettingsPage(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);

        User user = userService.getUserByLogin((String) model.get("login"));
        model.addAttribute("user", user);

        Instructor instructor = instructorService.getByUser(user);
        model.addAttribute("instructor", instructor);
        return "settings_instr";
    }

    @RequestMapping(value = "/instr-settings", method = RequestMethod.POST)
    public String processInstructorSettingsResults(ModelMap model,
                                                   @RequestParam(value = "description", required = false) String description,
                                                   @RequestParam(value = "awards", required = false) String awards,
                                                   @RequestParam(value = "qualification", required = false) String qualification,
                                                   @RequestParam(value = "experience", required = false) String experience,
                                                   @RequestParam(value = "user_id") int id) throws ParseException {
        Date date = null;
        if (experience != null && !experience.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            date = new Date(format.parse(experience).getTime());
        }


        instructorService.updateInstructor(id, description, awards, qualification, date);
        model.addAttribute("success", "Your instructor profile has been changed successfully");
        return "redirect:profile";
    }
}
