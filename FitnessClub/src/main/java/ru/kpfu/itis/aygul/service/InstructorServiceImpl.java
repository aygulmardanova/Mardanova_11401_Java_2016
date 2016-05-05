package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.repository.InstructorRepository;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;

import java.sql.Date;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getById(int id) {
        return instructorRepository.findOneById(id);
    }

    @Override
    public Instructor getByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return instructorRepository.findOneByUser(user);
    }

    @Override
    public Instructor getByNameAndSurname(String name, String surname) {
        Role instructor_role = Role.ROLE_INSTRUCTOR;
        User user = userRepository.findByNameAndSurnameAndRole(name, surname, instructor_role);
        return instructorRepository.findOneByUser(user);
    }

    @Override
    public Instructor getByUser(User user) {
        return instructorRepository.findOneByUser(user);
    }

    @Transactional
    @Override
    public void addInstructor(User user, String description, String qualification, String awards, Date experience) {
        Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructor.setAwards(awards);
        instructor.setDescription(description);
        instructor.setQualification(qualification);
        instructor.setExperience(experience);
        instructorRepository.save(instructor);
    }

    @Override
    public void updateInstructor(int user_id, String description, String awards, String qualification,
                                 Date experience) {
        User user = userRepository.findById(user_id);
        Instructor instructor = getByUser(user);
        if (description != null && !description.equals("")) {
            instructor.setDescription(description);
        }
        if (awards != null && !awards.equals("")) {
            instructor.setAwards(awards);
        }
        if (qualification != null && !qualification.equals("")) {
            instructor.setQualification(qualification);
        }
        if (experience != null) {
            instructor.setExperience(experience);
        }
        instructorRepository.save(instructor);
    }

}
