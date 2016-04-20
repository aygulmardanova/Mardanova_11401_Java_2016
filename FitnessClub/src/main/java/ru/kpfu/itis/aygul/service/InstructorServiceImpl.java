package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.InstructorRepository;
import ru.kpfu.itis.aygul.service.interfaces.InstructorService;

import java.sql.Date;
import java.util.List;

/**
 * Created by Айгуль on 20.04.2016.
 */
@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getById(int id) {
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor getByLogin(String login) {
        return instructorRepository.findByLogin(login);
    }

    @Override
    public Instructor getByNameAndSurname(String name, String surname) {
        return instructorRepository.findByNameAndSurname(name, surname);
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
}
