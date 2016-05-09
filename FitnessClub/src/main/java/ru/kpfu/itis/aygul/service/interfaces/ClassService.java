package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.ClassEntity;

import java.util.List;

/**
 * Created by aygulmardanova on 02.05.16.
 */
public interface ClassService {

    List<ClassEntity> getAll();

    ClassEntity getClassById(int id);

    ClassEntity getClassByName(String name);

    void addClass(String name, String description, String photo);

    boolean ifClassNameExists(String name);
}
