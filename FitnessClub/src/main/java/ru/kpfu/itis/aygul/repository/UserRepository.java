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

    //List<User> findByName(String name);

    List<User> findAll();

    User findByLogin(String login);

//    User save(User user);

    @Query("from User u where u.name = ?1 and u.surname = ?2 and role = ?3")
    User findByNameAndSurnameAndRole(String name, String surname, Role role);

    @Modifying
    @Query("update User u set u.email = ?1 where u.login = ?2")
    int setFixedEmailFor(String email, String login);

    @Modifying
    @Query("update User u set u.phoneNumber = ?1 where u.login = ?2")
    int setFixedPhoneNumberFor(String phoneNumber, String login);

    @Modifying
    @Query("update User u set u.phoneNumber = ?1 where u.id = ?2")
    int setFixedPhoneNumberFor(String phoneNumber, int id);

    @Modifying
    @Query("update User u set u.password = ?1 where u.login = ?2")
    int setFixedPasswordFor(String password, String login);

    @Modifying
    @Query("update User u set u.password = ?1 where u.id = ?2")
    int setFixedPasswordFor(String password, int id);

    @Modifying
    @Query("update User u set u.login = ?1 where u.id = ?2")
    int setFixedLoginFor(String password, int id);

    @Modifying
    @Query("update User u set u.name = ?1, u.surname = ?2 where u.id = ?3")
    int setFixedNameAndSurnameFor(String name, String surname, int id);

    @Modifying
    @Transactional
    @Query("update User u set u.role = ?1 where u.id = ?2")
    boolean setFixedRoleFor(Role role, int id);
}
