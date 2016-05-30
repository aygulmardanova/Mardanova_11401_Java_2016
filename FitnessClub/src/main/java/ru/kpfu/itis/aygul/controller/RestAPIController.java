package ru.kpfu.itis.aygul.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.api.ClassClient;
import ru.kpfu.itis.aygul.model.api.UserClient;
import ru.kpfu.itis.aygul.service.interfaces.ClassService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for methods from ClientApp.
 */
@RestController
@RequestMapping(value = "/rest/api")
public class RestAPIController {

    @Autowired
    UserService userService;
    @Autowired
    ClassService classService;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserClient login(ModelMap model, @PathVariable String login) {

        User user = userService.getUserByLogin(login);
        System.out.println("API User = " + user);

        if (user != null) {
            UserClient client = new UserClient(user);
            return client;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    @ResponseBody
    public List<ClassClient> getClasses() {
        List<ClassEntity> classEntityList = classService.getAllOrderByNameAsc();
        List<ClassClient> classes = new ArrayList<>();
        for (ClassEntity classEntity : classEntityList) {
            classes.add(new ClassClient(classEntity));
        }
        return classes;
    }

    @RequestMapping(value = "add_class", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addClass(ModelMap model, @RequestParam String name, @RequestParam String description) {

        if (classService.getClassByName(name) != null) {
            return false;
        } else {
            classService.addClass(name, description, null);
        }
        return true;
    }

    @RequestMapping(value = "/classes/delete", method = RequestMethod.POST)
    public void deleteClass(ModelMap model, @RequestParam Integer id) {
        classService.deleteClassById(id);
    }

    @RequestMapping(value = "/classes/edit", method = RequestMethod.POST)
    public void editClass(ModelMap model, @RequestParam String name, @RequestParam String description) {
        classService.editClassByName(name, description);
    }
}
