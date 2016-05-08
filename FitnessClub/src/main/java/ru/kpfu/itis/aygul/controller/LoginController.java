package ru.kpfu.itis.aygul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String returnLogin(ModelMap model,
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "message", required = false) String message) throws IOException {

        if ("true".equals(error)) {
            model.addAttribute("error_msg", "Wrong login or password");
            return "login";
        }
        String message1 = (String) model.get("message");
        if (message != null) {
            model.addAttribute("message", message1);
            return "login";
        }

        return "login";
    }

}
