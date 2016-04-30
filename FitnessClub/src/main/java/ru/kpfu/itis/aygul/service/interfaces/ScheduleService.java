package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Schedule;

import java.util.List;

/**
 * Created by Айгуль on 20.04.2016.
 */
public interface ScheduleService {

    List<Schedule> getAll();

    List<Schedule> getByClassByClassId(ClassEntity classByClassId);

    List<Schedule> getScheduleByStartTime(int startTime);

    List<ClassEntity> getClassesByStartTime(int startTime);

    List<Schedule> getScheduleByInstructor(Instructor instructor);

}
