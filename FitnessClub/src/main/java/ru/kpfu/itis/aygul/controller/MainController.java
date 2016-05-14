package ru.kpfu.itis.aygul.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.aygul.aspects.annotations.AuthUserName;
import ru.kpfu.itis.aygul.model.*;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.model.enums.WeekDay;
import ru.kpfu.itis.aygul.service.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ClassService classService;

    @Autowired
    ScheduleService scheduleService;

    private static Properties props = new Properties();


    private ModelMap addAllPropsIntoModel(ModelMap model) throws IOException {

        props.load(getClass().getResourceAsStream("/clubinfo.properties"));
        model.addAttribute("description", props.getProperty("club.description"));
        model.addAttribute("address", props.getProperty("club.address"));
        model.addAttribute("email", props.getProperty("club.email"));
        model.addAttribute("work_week_open", props.getProperty("club.work_week_open"));
        model.addAttribute("work_week_close", props.getProperty("club.work_week_close"));
        model.addAttribute("weekend_open", props.getProperty("club.weekend_open"));
        model.addAttribute("weekend_close", props.getProperty("club.weekend_close"));
        model.addAttribute("class_duration", props.getProperty("club.class_duration"));

        return model;
    }

    private ModelMap getModelWithScheduleData(ModelMap model) {

        HashMap<String, Schedule> monday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.MONDAY)) {
            monday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("monday", monday);

        HashMap<String, Schedule> tuesday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.TUESDAY)) {
            tuesday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("tuesday", tuesday);

        HashMap<String, Schedule> wednesday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.WEDNESDAY)) {
            wednesday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("wednesday", wednesday);

        HashMap<String, Schedule> thursday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.THURSDAY)) {
            thursday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("thursday", thursday);

        HashMap<String, Schedule> friday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.FRIDAY)) {
            friday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("friday", friday);

        HashMap<String, Schedule> saturday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.SATURDAY)) {
            saturday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("saturday", saturday);

        HashMap<String, Schedule> sunday = new HashMap<>();
        for (Schedule schedule: scheduleService.getScheduleByWeekday(WeekDay.SUNDAY)) {
            sunday.put(String.valueOf(schedule.getStartTime()), schedule);
        }
        model.put("sunday", sunday);

        List<String> startTimes = new ArrayList<>();
        for (int i = 9; i <= 13; i++) {
            startTimes.add(String.valueOf(i));
        }
        for (int i = 17; i <= 20; i++) {
            startTimes.add(String.valueOf(i));
        }

        model.put("startTimes", startTimes);

        return model;
    }

    private ModelMap getModelWithScheduleEditData(ModelMap model) {
        List<Instructor> instructors = instructorService.getAll();
        List<ClassEntity> classes = classService.getAll();
        List<WeekDay> weekDays = new ArrayList<>();
        weekDays.add(WeekDay.MONDAY);
        weekDays.add(WeekDay.TUESDAY);
        weekDays.add(WeekDay.WEDNESDAY);
        weekDays.add(WeekDay.THURSDAY);
        weekDays.add(WeekDay.FRIDAY);
        weekDays.add(WeekDay.SATURDAY);
        weekDays.add(WeekDay.SUNDAY);

        model.addAttribute("instructors", instructors);
        model.addAttribute("classes", classes);
        model.addAttribute("weekDays", weekDays);

        return model;
    }



    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {

        int min_price = subscriptionService.findMinPrice();
        model.put("min_price", min_price);
        return "main";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String returnUsersPage(ModelMap model) throws IOException {

        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String returnSchedulePage(ModelMap model) {
        model = getModelWithScheduleData(model);
        return "schedule";
    }

    @RequestMapping(value = "/schedule/edit", method = RequestMethod.GET)
    public String returnEditSchedulePage(ModelMap model) {

        model = getModelWithScheduleData(model);

        model = getModelWithScheduleEditData(model);
        return "schedule_edit";
    }


    @RequestMapping(value = "/schedule/edit", method = RequestMethod.POST)
    public String returnEditSchedulePage(ModelMap model, @RequestParam("startTime") int startTime,
                                         @RequestParam("instr_id") int instr_id,
                                         @RequestParam("class_id") int class_id,
                                         @RequestParam("weekDay") WeekDay weekDay) {
        String msg = "";
        Instructor instructor = instructorService.getById(instr_id);
        Schedule schedule = scheduleService.getScheduleByStartTimeAndDayOfWeek(startTime, weekDay);
        ClassEntity classEntity = classService.getClassById(class_id);

        if (schedule != null) {
            msg = startTime + ".00-" + startTime + ".55 on " + weekDay + " isn't free. \n" +
                    "Please, choose another time";
        } else {
            scheduleService.addSchedule(instructor, startTime, weekDay, classEntity);
            msg = "Class " + classEntity.getName() + " at " + startTime + ".00-" + startTime + ".55 on " + weekDay + " " +
                    "with instructor " + instructor.getUser().getName() + " " +
                    instructor.getUser().getSurname() + " added.";
        }
        model = getModelWithScheduleData(model);
        model = getModelWithScheduleEditData(model);

        model.put("message", msg);
        return "schedule_edit";
    }


    @RequestMapping(value = "/schedule/delete", method = RequestMethod.POST)
    public String deleteSchedule(ModelMap model, @RequestParam("startTime") int startTime,
                                         @RequestParam("day") WeekDay dayOfWeek) {

        scheduleService.deleteByStartTimeAndDayOfWeek(startTime, dayOfWeek);

        String msg = "Class at " + startTime + ".00 on " + dayOfWeek + " was deleted";

        model = getModelWithScheduleData(model);
        model = getModelWithScheduleEditData(model);

        model.put("message", msg);
        return "schedule_edit";
    }

    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    public String returnTrainersInfo(ModelMap model) throws IOException {

        List<Instructor> instructors = instructorService.getAll();
        model.addAttribute("instructors", instructors);
        return "trainers";
    }

    @RequestMapping(value = "/trainer/profile", method = RequestMethod.GET)
    public String returnTrainerPage(ModelMap model, @RequestParam("id") int id) throws IOException {

        Instructor instructor = instructorService.getById(id);
        model.addAttribute("instructor", instructor);
        return "trainer";
    }

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public String returnClassesPage(ModelMap model, @RequestParam(value = "message", required = false) String message) throws IOException {

        List<ClassEntity> classes;
        classes = classService.getAll();
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("classes", classes);

        return "classes";
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public String returnPricesPage(ModelMap model) throws IOException {

        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "prices";
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String returnAboutClubPage(ModelMap model) throws IOException {
        model = addAllPropsIntoModel(model);

        List<Subscription> subscriptions = subscriptionService.getAll();
        model.addAttribute("subscriptions", subscriptions);
        return "clubinfo";
    }

    @RequestMapping(value = "/about-us/download", method = RequestMethod.GET)
    public void downloadAboutClubInfoPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        Document document = new Document();

        try {
            model = addAllPropsIntoModel(model);
            response.setContentType("application/pdf");
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            document.addTitle("AboutFitClub");
            document.add(new Paragraph("             " + model.get("clubname")));
            document.add(new Paragraph((String) model.get("slogan")));
            document.add(new Paragraph("Contacts: "));
            document.add(new Paragraph("Phone: " + model.get("phone_number")));
            document.add(new Paragraph("Email: " + model.get("email")));
            document.add(new Paragraph("Address: " + model.get("address")));
            document.add(new Paragraph("Work hours: "));
            document.add(new Paragraph("  Mn-Fr: " + model.get("work_week_open") + " to " + model.get("work_week_close")));
            document.add(new Paragraph("  Sat-Sn: " + model.get("weekend_open") + " to " + model.get("weekend_close")));
            document.add(new Paragraph("Classes continues " + model.get("class_duration") + " minutes"));

            document.addCreationDate();
            document.addAuthor("Mardanova Aygul");
            document.addCreator("fitness-club");
            document.addSubject("Main information about Fitness Club");
            document.close();
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
    }




}
