package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByLogin(String login);

    boolean checkUser(String login, String password);

    boolean checkUser(int id, String password);

    boolean changeEmail(String login, String email);

    boolean changePassword(String login, String old_password, String new_password);

    void changeRole(Role role, int user_id);

    void saveUser(String login, String password, String email, String name,
                  String surname, String photo, String phone_number, Role role);

    void updateUser(int user_id, String photo, String login, String name, String surname,
                    String email, String new_password, String new_password_repeat,
                    String phoneNumber);

}
