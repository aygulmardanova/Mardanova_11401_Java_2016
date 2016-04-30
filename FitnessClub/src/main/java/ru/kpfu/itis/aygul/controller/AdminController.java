package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.service.ProbablyInstructorServiceImpl;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Айгуль on 24.04.2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProbablyInstructorService probablyInstructorService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getAdminProfile(ModelMap model) {
        return "admin_profile";
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String getRequests(ModelMap model) {
        List<User> probablyInstructors;
        probablyInstructors = probablyInstructorService.getAllUsers();
        model.addAttribute("pi", probablyInstructors);
        return "requests";
    }

    @RequestMapping(value = "/requests'", method = RequestMethod.POST)
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
