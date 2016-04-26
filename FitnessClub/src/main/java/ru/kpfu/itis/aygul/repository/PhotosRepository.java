package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.Photos;

import java.util.List;

/**
 * Created by Айгуль on 22.04.2016.
 */
@Repository
public interface PhotosRepository extends JpaRepository<Photos, Integer>{

    Photos findById(int id);

    List<Photos> findAll();

}
