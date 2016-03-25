package ru.kpfu.itis.aygul.hw15.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Map;

@Controller
@RequestMapping
public class ProcessController {

    @RequestMapping(path = "/process", method = RequestMethod.GET)
    public String processPage() {
        return "process";
    }


    @RequestMapping(path = "/process", method = RequestMethod.POST)
    public String processPost(ModelMap model, @RequestParam String text, @RequestParam String option) {
        int result = 0;

        if ("Symbols".equals(option)) {
            result = text.length();
        } else
        if ("Words".equals(option)) {
            String[] words = text.split(" ");
            result = words.length;
        } else
        if ("Sentences".equals(option)) {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?') {
                    result++;
                }
            }
        } else {
            String[] paragraphs = text.split("\n");
            result = paragraphs.length;
        }
        model.put("text", text);
        model.put("option", option.toLowerCase());
        model.put("result", result);
        return "redirect:" + "result";
    }

    @RequestMapping(path = "/result", method = RequestMethod.GET)
    public String showResult(ModelMap model, @RequestParam String text, @RequestParam String option,
                             @RequestParam String result) {
        model.put("text", text);
        model.put("option", option.toLowerCase());
        model.put("result", result);
        return "result";
    }
}
