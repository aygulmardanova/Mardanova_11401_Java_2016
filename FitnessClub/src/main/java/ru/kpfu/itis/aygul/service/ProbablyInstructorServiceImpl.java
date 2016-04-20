package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.ProbablyInstructorRepository;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Айгуль on 19.04.2016.
 */
public class ProbablyInstructorServiceImpl implements ProbablyInstructorService {

    @Autowired
    ProbablyInstructorRepository probablyInstructorRepository;

    public List<ProbablyInstructor> getAll() {
        return probablyInstructorRepository.findAll();
    }

    public List<User> getAllUsers() {
        List<ProbablyInstructor> piList = getAll();
        List<User> users = new ArrayList<User>();
        for (ProbablyInstructor pi : piList) {
            users.add(pi.getUser());
        }
        return users;
    }

}
