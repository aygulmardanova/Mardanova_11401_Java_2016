package ru.kpfu.itis.aygul.hw14.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * baidu.com, bing.com, yahoo.com, aol.com
 */
@Controller
@RequestMapping("/{system}.com/search")
public class SearchController {

    @RequestMapping(method = RequestMethod.GET)
    public String searchPage(ModelMap model, @PathVariable String system) {
        String name;
        String action;
        if ("baidu".equals(system)) {
            action = "www." + system + ".com/s";
            name = "wd";
        } else
        if ("bing".equals(system)) {
            action = "www." + system + ".com/search";
            name = "q";
        } else
        if ("yahoo".equals(system)) {
            action = "search." + system + ".com/search";
            name = "q";
        } else
        if ("aol".equals(system)) {
            action = "search.aol.com/aol/search";
            name = "q";
        } else {
            return "incorrect";
        }
        model.put("system", system);
        model.put("action", action);
        model.put("name", name);
        return "search";
    }

}
