package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

//    ProbablyInstructor save(ProbablyInstructor probablyInstructor);

    ProbablyInstructor findById(int id);

    ProbablyInstructor findByUser(User user);

    @Query("delete from ProbablyInstructor p where p.user = ?1")
    void deleteByUser(User user);
}
