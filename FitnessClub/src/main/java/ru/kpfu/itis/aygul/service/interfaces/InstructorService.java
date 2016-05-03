package ru.kpfu.itis.aygul.service.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by Айгуль on 20.04.2016.
 */
public interface InstructorService {
    List<Instructor> getAll();

    Instructor getById(int id);

    Instructor getByLogin(String login);

    Instructor getByNameAndSurname(String name, String surname);

    Instructor getByUser(User user);

    void addInstructor(User user, String description, String qualification,
                       String awards, Date experience);
}
