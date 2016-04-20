package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.User;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByLogin(String login);

    boolean checkUser(String login, String password);

    boolean changeEmail(String login, String email);

    boolean changePassword(String login, String old_password, String new_password);

    void saveUser(String login, String password, String email,
                  String name, String surname, String photo, String phone_number);

}
