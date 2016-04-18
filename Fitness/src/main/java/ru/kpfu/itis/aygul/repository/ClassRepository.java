package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.aygul.model.Level;

import java.util.List;


public interface ClassRepository extends JpaRepository<Class, Integer> {

    List<Class> findAll();

    List<Class> findByLevel(Level level);

    Class findByName(String name);

    Class findById(int id);
}
