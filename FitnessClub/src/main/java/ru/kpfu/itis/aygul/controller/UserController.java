package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Subscription;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.PurchaseService;
import ru.kpfu.itis.aygul.service.interfaces.SubscriptionService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static ru.kpfu.itis.aygul.controller.SignupController.checkWithRegExp;

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

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    PurchaseService purchaseService;

    public static String savePhoto(MultipartFile photo, String type) {

        final String SAVE_DIR_USER = "users";

        final String SAVE_DIR_CLASS = "classes";

        String filename = null;

        final String SAVE_DIR;
        if ("class".equals(type)) {
            SAVE_DIR = SAVE_DIR_CLASS;
        } else if ("user".equals(type)) {
            SAVE_DIR = SAVE_DIR_USER;
        } else {
            return null;
        }

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                filename = photo.getOriginalFilename();
                String rootPath = "/Users/aygulmardanova/IdeaProjects/FitnessClub/target/fitnessclub-1.0-SNAPSHOT/images";
                File dir = new File(rootPath + File.separator + SAVE_DIR);
                System.out.println("Root path1 - target: " + rootPath);

                String rootPath2 = "/Users/aygulmardanova/IdeaProjects/FitnessClub/src/main/webapp/images";
                File dir2 = new File(rootPath2 + File.separator + SAVE_DIR);

                System.out.println("Root path2: " + rootPath2);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + filename);
                File uploadedFile2 = new File(dir2.getAbsolutePath() + File.separator + filename);

                System.out.println("uploadedFile" + uploadedFile.toPath());
                System.out.println("uploadedFile2" + uploadedFile2.toPath());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream(uploadedFile2));
                stream2.write(bytes);
                stream2.flush();
                stream2.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filename;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String returnProfilePage(ModelMap model,
                                    @RequestParam(value = "bought", required = false) String bought,
                                    @RequestParam(value = "success", required = false) String success) {

        if (success != null) {
            model.addAttribute("success", success);
        }

        if (bought != null) {
            model.addAttribute("bought", bought);
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
    public String returnSettingsPage(ModelMap model) {

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
                                    @RequestParam("user_id") int id) {

        String photoName = savePhoto(photo, "user");

        if (login != null && userService.getUserByLogin(login) != null) {
            model.addAttribute("message", "Login " + login + " is busy");
            return "redirect:settings";
        }
        if (email != null && userService.getUserByEmail(email) != null) {
            model.addAttribute("message", "Email " + email + " is busy");
            return "redirect:settings";
        }

        if (!checkWithRegExp(login, "login")) {
            model.addAttribute("message", "Login " + login + " is incorrect");
            return "redirect:settings";
        }
        if (!checkWithRegExp(new_password, "pass")) {
            model.addAttribute("message", "Incorrect password format: " + new_password);
            return "redirect:settings";
        }
        if (!checkWithRegExp(email, "email")) {
            model.addAttribute("message", "Email " + email + " is incorrect");
            return "redirect:settings";
        }
        if (!checkWithRegExp(phone, "phone")) {
            model.addAttribute("message", "Phone " + phone + " is incorrect");
            return "redirect:settings";
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
    public String returnInstructorSettingsPage(ModelMap model) {

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

    @RequestMapping(value = "/buy-subscr", method = RequestMethod.GET)
    public String returnBuySubscrPage(ModelMap model) {

        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);

        return "buy_subscr";
    }

    @RequestMapping(value = "/buy-subscr", method = RequestMethod.POST)
    public String buySubscrMethod(ModelMap model, @RequestParam(value = "login") String login,
                                  @RequestParam(value = "subscr_id") int subscr_id,
                                  @RequestParam(value = "validity") int validity) {

        purchaseService.addPurchase(login, subscr_id);

        model.addAttribute("bought", "You have just bought a subscription with validity " + validity + " months");
        return "redirect:profile";
    }
}
