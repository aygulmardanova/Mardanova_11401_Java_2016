package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.api.UserClient;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

/**
 * Created by aygulmardanova on 27.05.16.
 */
@RestController
@RequestMapping(value = "/rest/api")
public class RestAPIController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserClient login(ModelMap model, @PathVariable String login) {

        User user = userService.getUserByLogin(login);
        System.out.println("User = " + user);

        if (user != null) {
            UserClient client = new UserClient(user);
            System.out.println(client);
            return client;
        } else {
            return null;
        }
    }



}
