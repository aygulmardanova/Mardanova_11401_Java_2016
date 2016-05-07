package ru.kpfu.itis.aygul.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.aygul.aspects.annotations.AuthUserName;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    private static final Logger logger = Logger.getLogger(UserController.class);
    
    @AuthUserName
    private ModelMap addLoginIntoModel(ModelMap model) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if (login != null && !login.equals("")) {
            model.addAttribute("login", login);
        }
        return model;
    }


    public String savePhoto(MultipartFile photo) {

        String filename = null;
        final String SAVE_DIR = "users";

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                filename = photo.getOriginalFilename();
                logger.info("User is going to upload photo " + filename);
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
    public String returnProfilePage(ModelMap model) throws IOException {
        model = addLoginIntoModel(model);

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
        model = addLoginIntoModel(model);
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
        model = addLoginIntoModel(model);
        String photoName = savePhoto(photo);

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
        model = addLoginIntoModel(model);

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
