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
import ru.kpfu.itis.aygul.aspects.annotations.AuthUserName;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.service.ProbablyInstructorServiceImpl;
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

    private static Properties props = new Properties();

    @AuthUserName
    private ModelMap addLoginIntoModel(ModelMap model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if (login != null && !login.equals("")) {
            model.addAttribute("login", login);
            logger.info("Authenticated user's name: " + login);

        }

        return model;
    }

    private ModelMap addMainPropsIntoModel(ModelMap model) throws IOException {
        props.load(getClass().getResourceAsStream("/clubinfo.properties"));
        model.addAttribute("clubname", props.getProperty("club.name"));
        model.addAttribute("slogan", props.getProperty("club.slogan"));
        model.addAttribute("phone_number", props.getProperty("club.phone_number"));

        model = addLoginIntoModel(model);

        return model;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getAdminProfile(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);
        User user = userService.getUserByLogin((String) model.get("login"));
        model.addAttribute("user", user);
        return "redirect:user/profile";
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String getRequests(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);

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
    public String returnScheduleModifyingPage(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);
        return "schedule";
    }

    @RequestMapping(value = "/edit-prices", method = RequestMethod.GET)
    public String returnEditPricesPage(ModelMap model) throws IOException {
        model = addMainPropsIntoModel(model);
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
                                             @RequestParam(value = "price") int price)
            throws IOException {
        subscriptionService.updateSubscriptionPriceById(id, price);

        model = addMainPropsIntoModel(model);
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
}
