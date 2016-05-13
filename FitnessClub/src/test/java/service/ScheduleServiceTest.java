package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Schedule;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.WeekDay;
import ru.kpfu.itis.aygul.repository.ScheduleRepository;
import ru.kpfu.itis.aygul.service.ScheduleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 13.05.16.
 */
public class ScheduleServiceTest {

    private static ScheduleServiceImpl scheduleService;
    private static ScheduleRepository scheduleRepository;

    private static ClassEntity classEntity;
    private static Instructor instructor;
    private static Instructor instructor2;
    private static int startTime;
    private static WeekDay dayOfWeek;

    private static Schedule schedule;
    private static List<Schedule> schedules;
    private static List<ClassEntity> classes;

    private static final int incor_start = 25;

    @BeforeClass
    public static void beforeMethod() {
        classEntity = new ClassEntity();
        instructor = new Instructor();
        instructor2 = new Instructor();
        User user = new User();

        classEntity.setName("tanci");
        instructor.setUser(user);
        instructor.setDescription("aaa");
        instructor2.setUser(new User());
        startTime = 9;
        dayOfWeek = WeekDay.MONDAY;
        classes = new ArrayList<>();
        classes.add(classEntity);

        schedule = new Schedule();
        schedule.setInstructor(instructor);
        schedule.setDayOfWeek(dayOfWeek);
        schedule.setStartTime(startTime);
        schedule.setClassByClassId(classEntity);

        schedules = new ArrayList<>();
        schedules.add(schedule);

        scheduleService = new ScheduleServiceImpl();
        scheduleRepository = mock(ScheduleRepository.class);
        when(scheduleRepository.findAll()).thenReturn(schedules);

        when(scheduleRepository.findById(anyInt())).thenReturn(null);
        when(scheduleRepository.findById(schedule.getId())).thenReturn(schedule);

        when(scheduleRepository.findByClass(any(ClassEntity.class))).thenReturn(null);
        when(scheduleRepository.findByClass(classEntity)).thenReturn(schedules);

        when(scheduleRepository.findByInstructor(any(Instructor.class))).thenReturn(null);
        when(scheduleRepository.findByInstructor(instructor)).thenReturn(schedules);

        when(scheduleRepository.findByStartTime(anyInt())).thenReturn(null);
        when(scheduleRepository.findByStartTime(schedule.getStartTime())).thenReturn(schedules);

        when(scheduleRepository.findOneByStartTimeAndDayOfWeek(anyInt(), any(WeekDay.class))).thenReturn(null);
        when(scheduleRepository.findOneByStartTimeAndDayOfWeek(schedule.getStartTime(), schedule.getDayOfWeek())).thenReturn(schedule);

        when(scheduleRepository.findByDayOfWeekOrderByStartTimeAsc(any(WeekDay.class))).thenReturn(schedules);

        scheduleService.setScheduleRepository(scheduleRepository);
    }

    @Test
    public void getAllShouldReturnCorrectList() {
        Assert.assertEquals(schedules, scheduleService.getAll());
    }

    @Test
    public void getScheduleByStartTimeShouldReturnCorrectListIfCorrectTime() {
        Assert.assertEquals(schedules, scheduleService.getScheduleByStartTime(schedule.getStartTime()));
    }

    @Test
    public void getScheduleByStartTimeShouldReturnNullIfIncorrectTime() {
        Assert.assertNull(scheduleService.getScheduleByStartTime(incor_start));
    }

    @Test
    public void getByClassByClassIdShouldReturnCorrectListForExClass() {
        Assert.assertEquals(schedules, scheduleService.getByClassByClassId(schedule.getClassByClassId()));
    }

    @Test
    public void getByClassByClassIdShouldReturnNullForIncorClass() {
        Assert.assertNull(scheduleService.getByClassByClassId(new ClassEntity()));
    }

    @Test
    public void getScheduleByStartTimeAndDayOfWeekShouldReturnCorrSch() {
        Assert.assertEquals(schedule, scheduleService.getScheduleByStartTimeAndDayOfWeek(schedule.getStartTime(), schedule.getDayOfWeek()));
    }

    @Test
    public void getScheduleByStartTimeAndDayOfWeekShouldReturnNullIfIncorTime() {
        Assert.assertNull(scheduleService.getScheduleByStartTimeAndDayOfWeek(incor_start, schedule.getDayOfWeek()));
    }

    @Test
    public void getClassesByStartTimeShouldReturnCorrListOfClassesForCorrTime() {
        Assert.assertEquals(classes, scheduleService.getClassesByStartTime(schedule.getStartTime()));
    }

    @Test
    public void getScheduleByInstructorShouldReturnCorrectListForInstr() {
        Assert.assertEquals(schedules, scheduleService.getScheduleByInstructor(schedule.getInstructor()));
    }

    @Test
    public void getScheduleByInstructorShouldReturnNullForIncorInstr() {
        Assert.assertNull(scheduleService.getScheduleByInstructor(instructor2));
    }

    @Test
    public void getScheduleByWeekdayShouldReturnCorrectList() {
        Assert.assertEquals(schedules, scheduleService.getScheduleByWeekday(schedule.getDayOfWeek()));
    }

}
