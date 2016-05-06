package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Schedule;
import ru.kpfu.itis.aygul.model.enums.WeekDay;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAll();

    Schedule findById(int id);

    List<Schedule> findByDayOfWeekOrderByStartTimeAsc(WeekDay dayOfWeek);

    List<Schedule> findByInstructor(Instructor instructor);

    List<Schedule> findByStartTime(int startTime);

    @Query("select s from Schedule s where s.classByClassId = ?1")
    List<Schedule> findByClass(ClassEntity classByClassId);

//    List<Schedule> findAllByDayOfWeek(WeekDay dayOfWeek);

}
