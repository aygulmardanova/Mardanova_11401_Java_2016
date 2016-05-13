package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.ProbablyInstructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
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
        ProbablyInstructor probablyInstructor = probablyInstructorRepository.findOneByUser(user);
        return probablyInstructor != null;
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Override
    public void rejectProbablyInstructor(User user) {

        user.setRole(Role.ROLE_USER);
        ProbablyInstructor probablyInstructor = probablyInstructorRepository.findOneByUser(user);
        userRepository.save(user);
        probablyInstructorRepository.delete(probablyInstructor);
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void rejectProbablyInstructor(int user_id) {
        User user = userRepository.findById(user_id);
        rejectProbablyInstructor(user);
    }


    @Secured("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void acceptProbablyInstructor(int user_id) {
        User user = userRepository.findById(user_id);
        acceptProbablyInstructor(user);

    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void acceptProbablyInstructor(User user) {

        user.setRole(Role.ROLE_INSTRUCTOR);
        userRepository.save(user);   //set the user's role as ROLE_INSTRUCTOR
        Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructorRepository.save(instructor);
        ProbablyInstructor probablyInstructor = probablyInstructorRepository.findOneByUser(user);
        probablyInstructorRepository.delete(probablyInstructor);

    }

    @Override
    public void addProbablyInstructor(User user) {
        ProbablyInstructor probablyInstructor = new ProbablyInstructor();
        probablyInstructor.setUser(user);
        probablyInstructorRepository.save(probablyInstructor);
    }


    public ProbablyInstructorRepository getProbablyInstructorRepository() {
        return probablyInstructorRepository;
    }

    public void setProbablyInstructorRepository(ProbablyInstructorRepository probablyInstructorRepository) {
        this.probablyInstructorRepository = probablyInstructorRepository;
    }

}
