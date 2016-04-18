package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.Schedule;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAll();

    Schedule findById(int id);

    List<Schedule> findByDayOfWeek(String dayOfWeek);

    List<Schedule> findByInstructor(Instructor instructor);

    List<Schedule> findByStartTime(int startTime);

    @Query("select s from Schedule s where s.classByClassId = ?1")
    List<Schedule> findByClass(Class classByClassId);


}
