package ru.kpfu.itis.aygul.hw14.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/getdate")
public class GetDateController {

    private static final String DATE_FORMAT = "EEE, dd MMMM yyyy HH:mm:ss";

    @RequestMapping
    public String getDate(ModelMap model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        String date = simpleDateFormat.format(new Date());
        model.put("date", date);
        return "getdate";
    }
}
