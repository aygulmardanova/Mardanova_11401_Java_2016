package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    List<Instructor> findAll();

    Instructor findOneById(int id);

    Instructor findOneByUser(User user);

    @Query("select i from Instructor i order by i.experience asc")
    List<Instructor> findAllOrderByExperienceAsc();

    @Query("select i from Instructor i order by i.experience desc")
    List<Instructor> findAllOrderByExperienceDesc();

}
