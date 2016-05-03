package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.repository.ClassRepository;
import ru.kpfu.itis.aygul.service.interfaces.ClassService;

import java.util.List;

/**
 * Created by aygulmardanova on 02.05.16.
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<ClassEntity> getAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassEntity getClassById(int id) {
        return classRepository.findById(id);
    }

    @Override
    public ClassEntity getClassByName(String name) {
        return classRepository.findByName(name);
    }
}