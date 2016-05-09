package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Schedule;
import ru.kpfu.itis.aygul.model.enums.WeekDay;
import ru.kpfu.itis.aygul.repository.ScheduleRepository;
import ru.kpfu.itis.aygul.service.interfaces.ScheduleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Айгуль on 20.04.2016.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getByClassByClassId(ClassEntity classByClassId) {
        return scheduleRepository.findByClass(classByClassId);
    }

    @Override
    public List<Schedule> getScheduleByStartTime(int startTime) {
        return scheduleRepository.findByStartTime(startTime);
    }

    @Override
    public Schedule getScheduleByStartTimeAndDayOfWeek(int startTime, WeekDay dayOfWeek) {
        return scheduleRepository.findOneByStartTimeAndDayOfWeek(startTime, dayOfWeek);
    }

    @Override
    public List<ClassEntity> getClassesByStartTime(int startTime) {
        List<Schedule> schedules = scheduleRepository.findByStartTime(startTime);
        List<ClassEntity> classes = new ArrayList<ClassEntity>();
        for (Schedule schedule : schedules) {
            classes.add(schedule.getClassByClassId());
        }
        return classes;
    }

    @Override
    public List<Schedule> getScheduleByInstructor(Instructor instructor) {
        return scheduleRepository.findByInstructor(instructor);
    }

    @Override
    public List<Schedule> getScheduleByWeekday(WeekDay weekday) {
        return scheduleRepository.findByDayOfWeekOrderByStartTimeAsc(weekday);
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void addSchedule(Instructor instructor, int startTime, WeekDay dayOfWeek, ClassEntity classEntity) {
        Schedule schedule = new Schedule();
        schedule.setClassByClassId(classEntity);
        schedule.setDayOfWeek(dayOfWeek);
        schedule.setInstructor(instructor);
        schedule.setStartTime(startTime);
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteByStartTimeAndDayOfWeek(int startTime, WeekDay dayOfWeek) {
        Schedule schedule = scheduleRepository.findOneByStartTimeAndDayOfWeek(startTime, dayOfWeek);
        scheduleRepository.delete(schedule);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }
}
