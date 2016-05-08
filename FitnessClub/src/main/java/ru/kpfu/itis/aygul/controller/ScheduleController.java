package ru.kpfu.itis.aygul.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import ru.kpfu.itis.aygul.model.Schedule;
import ru.kpfu.itis.aygul.model.enums.WeekDay;
import ru.kpfu.itis.aygul.service.interfaces.ClassService;
import ru.kpfu.itis.aygul.service.interfaces.ScheduleService;


/**
 * Created by aygulmardanova on 06.05.16.
 */

@Controller
@RequestMapping("")
public class ScheduleController extends AbstractController {

    @Autowired
    ClassService classService;

    @Autowired
    ScheduleService scheduleService;

    private static final Logger logger = Logger.getLogger(ScheduleController.class);

    @RequestMapping(value = "/schedule/download", method = RequestMethod.GET)
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        String output =
                ServletRequestUtils.getStringParameter(request, "output");

        Map<String, Object> scheduleData = new HashMap<>();

        HashMap<String, Schedule> monday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.MONDAY)) {
            monday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("monday", monday);
        System.out.println("Monday at 9: " + monday.get("9"));

        HashMap<String, Schedule> tuesday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.TUESDAY)) {
            tuesday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("tuesday", tuesday);

        HashMap<String, Schedule> wednesday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.WEDNESDAY)) {
            wednesday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("wednesday", wednesday);

        HashMap<String, Schedule> thursday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.THURSDAY)) {
            thursday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("thursday", thursday);

        HashMap<String, Schedule> friday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.FRIDAY)) {
            friday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("friday", friday);

        HashMap<String, Schedule> saturday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.SATURDAY)) {
            saturday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("saturday", saturday);

        HashMap<String, Schedule> sunday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.SUNDAY)) {
            sunday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        scheduleData.put("sunday", sunday);

        List<String> startTimes = new ArrayList<>();
        for (int i = 9; i <= 13; i++) {
            startTimes.add(String.valueOf(i));
        }
        for (int i = 17; i <= 20; i++) {
            startTimes.add(String.valueOf(i));
        }

        scheduleData.put("clubname", "Fitness Club");
        scheduleData.put("slogan", "Forever fit, forever strong!");
        scheduleData.put("phone_number", "9-917-000-000");

        scheduleData.put("startTimes", startTimes);

        response.setContentType("application/pdf");

        response.getOutputStream();

        if (output ==null || "".equals(output)) {
            //return normal view (viewName, modelName, modelObject)
            return new ModelAndView("schedule", "scheduleData", scheduleData);

        } else if("PDF".equals(output.toUpperCase())) {
            //return excel view
            return new ModelAndView("PdfSchedule", "scheduleData", scheduleData);

        } else {
            //return normal view
            return new ModelAndView("schedule", "scheduleData", scheduleData);

        }

    }
}
