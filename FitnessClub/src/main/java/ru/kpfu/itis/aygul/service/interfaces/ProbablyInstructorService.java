package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;

import java.util.List;

/**
 * Created by Айгуль on 19.04.2016.
 */
public interface ProbablyInstructorService {

    List<ProbablyInstructor> getAll();

    List<User> getAllUsers();

    boolean isProbablyInstructor(User user);

    void rejectProbablyInstructor(User user);

    void saveProbablyInstructorAsInstructor(User user);


}
