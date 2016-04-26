package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.InstructorRepository;
import ru.kpfu.itis.aygul.repository.ProbablyInstructorRepository;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Айгуль on 19.04.2016.
 */
@Service
public class ProbablyInstructorServiceImpl implements ProbablyInstructorService {
    @Autowired
    ProbablyInstructorRepository probablyInstructorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<ProbablyInstructor> getAll() {
        return probablyInstructorRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        List<ProbablyInstructor> piList = getAll();
        List<User> users = new ArrayList<User>();
        for (ProbablyInstructor pi : piList) {
            users.add(pi.getUser());
        }
        return users;
    }

    @Override
    public boolean isProbablyInstructor(User user) {
        ProbablyInstructor probablyInstructor = probablyInstructorRepository.findByUser(user);
        return probablyInstructor != null;
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Override
    public void rejectProbablyInstructor(User user) {
        probablyInstructorRepository.deleteByUser(user);
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void acceptProbablyInstructor(int user_id) {
        User user = userRepository.findById(user_id);
        Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructorRepository.save(instructor);
        probablyInstructorRepository.deleteByUser(user);
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void acceptProbablyInstructor(User user) {
        acceptProbablyInstructor(user.getId());
    }

    @Override
    public void addProbablyInstructor(User user) {
        ProbablyInstructor probablyInstructor = new ProbablyInstructor();
        probablyInstructor.setUser(user);
        probablyInstructorRepository.save(probablyInstructor);
    }

}
