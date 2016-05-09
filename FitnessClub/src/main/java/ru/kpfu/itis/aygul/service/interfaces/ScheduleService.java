package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Schedule;
import ru.kpfu.itis.aygul.model.enums.WeekDay;

import java.util.List;

/**
 * Created by Айгуль on 20.04.2016.
 */
public interface ScheduleService {

    List<Schedule> getAll();

    List<Schedule> getByClassByClassId(ClassEntity classByClassId);

    List<Schedule> getScheduleByStartTime(int startTime);

    Schedule getScheduleByStartTimeAndDayOfWeek(int startTime, WeekDay dayOfWeek);

    List<ClassEntity> getClassesByStartTime(int startTime);

    List<Schedule> getScheduleByInstructor(Instructor instructor);

    List<Schedule> getScheduleByWeekday(WeekDay weekday);

    void addSchedule(Instructor instructor, int startTime, WeekDay dayOfWeek, ClassEntity classEntity);

    void deleteByStartTimeAndDayOfWeek(int startTime, WeekDay dayOfWeek);

    void deleteSchedule(Schedule schedule);
}
