package ru.kpfu.itis.aygul.repository;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.Instructor;

import java.util.List;

/**
 * Created by Айгуль on 20.04.2016.
 */
@Repository
public interface InstructorRepository {

    List<Instructor> findAll();

    Instructor findById(int id);

    Instructor findByLogin(String login);

    Instructor findByNameAndSurname(String name, String surname);

    List<Instructor> findWhereDescriptionIsNullOrAwardsIsNullOrExperienceIsNull();

    void save(Instructor instructor);
}
