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

    void rejectProbablyInstructor(int user_id);

    void rejectProbablyInstructor(User user);

    void acceptProbablyInstructor(int user_id);

    void acceptProbablyInstructor(User user);

    void addProbablyInstructor(User user);


}
