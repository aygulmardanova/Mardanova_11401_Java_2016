package ru.kpfu.itis.aygul.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.aygul.aspects.annotations.AuthUserName;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.service.ProbablyInstructorServiceImpl;
import ru.kpfu.itis.aygul.service.interfaces.ClassService;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;
import ru.kpfu.itis.aygul.service.interfaces.SubscriptionService;
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

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    ProbablyInstructorService probablyInstructorService;

    @Autowired
    UserService userService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ClassService classService;


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getAdminProfile(ModelMap model) {
        User user = userService.getUserByLogin((String) model.get("login"));
        model.addAttribute("user", user);
        return "redirect:user/profile";
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String getRequests(ModelMap model) {

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

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String returnScheduleModifyingPage(ModelMap model) {
        return "schedule";
    }

    @RequestMapping(value = "/edit-prices", method = RequestMethod.GET)
    public String returnEditPricesPage(ModelMap model) {
        if (model.get("message") != null) {
            model.addAttribute("message", model.get("message"));
        }
        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "prices_edit";
    }

    @RequestMapping(value = "/edit-prices", method = RequestMethod.POST)
    public String processEditingOfPricesPage(ModelMap model,
                                             @RequestParam(value = "id") int id,
                                             @RequestParam(value = "price") int price) {
        subscriptionService.updateSubscriptionPriceById(id, price);

        return "redirect:edit-prices";
    }

    @RequestMapping(value = "/delete-subscr/$[id}", method = RequestMethod.GET)
    public String deleteSubscription(ModelMap model, @PathVariable(value = "id") int id) {

        subscriptionService.deleteSubscription(id);
        return "redirect:edit-prices";
    }

    @RequestMapping(value = "/add-subscr", method = RequestMethod.POST)
    public String addSubscription(ModelMap model, @RequestParam(value = "validity") int validity,
                                  @RequestParam(value = "price") int price) {

        if (subscriptionService.ifValidityAlreadyExists(validity)) {
            model.addAttribute("message", "Subscription with validity = " + validity + " is already exists");
            return "redirect:edit-prices";
        }
        subscriptionService.addSubscription(validity, price);
        return "redirect:edit-prices";
    }

    @RequestMapping(value = "/add-class", method = RequestMethod.GET)
    public String returnAddClassPage(ModelMap model) {

        return "add_class";
    }

    @RequestMapping(value = "/add-class", method = RequestMethod.POST)
    public String addClassMethod(ModelMap model,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "photo", required = false) MultipartFile photo) {

        String photoName = UserController.savePhoto(photo, "class");
        if (classService.ifClassNameExists(name)) {
            String message = "Class " + name + " is already exists";
            model.addAttribute("message", message);
            return "add_class";
        }
        classService.addClass(name, description, photoName);
        model.addAttribute("message", "Class " + name + " is successfully added");
        return "redirect:/classes";
    }
}
