package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.ClassEntity;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

    List<ClassEntity> findAll();

    ClassEntity findByName(String name);

    ClassEntity findById(int id);

}
