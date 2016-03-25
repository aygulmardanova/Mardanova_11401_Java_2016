package ru.kpfu.itis.aygul.hw14.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Айгуль on 21.03.2016.
 */

@Controller
@RequestMapping("/mult/{oper1}/{oper2}")
public class MultController {

    @RequestMapping(method = RequestMethod.GET)
    public String multResult(ModelMap model, @PathVariable String oper1, @PathVariable String oper2) {
        if ("".equals(oper1) || "".equals(oper2)) {
            return "incorrect";
        }
        int op1 = Integer.valueOf(oper1);
        int op2 = Integer.valueOf(oper2);
        int result = op1 * op2;
        model.put("result", oper1 + " * " + oper2 + " = " + result);
        return "result";
    }
}
