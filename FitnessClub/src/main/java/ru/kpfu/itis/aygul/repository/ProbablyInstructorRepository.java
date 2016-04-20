package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;

import java.util.List;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Repository
public interface ProbablyInstructorRepository extends JpaRepository<ProbablyInstructor, Integer> {

    List<ProbablyInstructor> findAll();

}
