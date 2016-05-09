package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.Instructor;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findById(Integer id);

    List<User> findAll();

    User findByLogin(String login);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleOrderByNameAsc(Role role);

    List<User> findAllByRoleOrderByNameDesc(Role role);

    List<User> findAllByRoleOrderBySurnameAsc(Role role);

    List<User> findAllByRoleOrderBySurnameDesc(Role role);

    @Query("from User u where u.name = ?1 and u.surname = ?2 and role = ?3")
    User findByNameAndSurnameAndRole(String name, String surname, Role role);

}
