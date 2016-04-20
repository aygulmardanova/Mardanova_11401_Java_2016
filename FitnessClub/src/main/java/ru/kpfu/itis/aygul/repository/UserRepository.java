package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.aygul.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findById(Integer id);

    //List<User> findByName(String name);

    List<User> findAll();

    User findByLogin(String login);

    User save(User user);

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
}
