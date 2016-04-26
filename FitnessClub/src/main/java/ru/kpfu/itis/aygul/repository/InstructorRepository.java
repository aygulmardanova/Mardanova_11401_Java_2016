package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    List<Instructor> findAll();

    Instructor findOneById(int id);

//    Instructor findByUserId(int users_id);

    Instructor findOneByUser(User user);

//    List<Instructor> findWhereDescriptionIsNullOrAwardsIsNullOrExperienceIsNull();

    Instructor save(Instructor instructor);
}
