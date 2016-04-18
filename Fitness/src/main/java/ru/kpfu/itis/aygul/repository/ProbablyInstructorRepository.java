package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;

/**
 * Created by Айгуль on 17.04.2016.
 */
public interface ProbablyInstructorRepository extends JpaRepository<ProbablyInstructor, Integer> {
}
