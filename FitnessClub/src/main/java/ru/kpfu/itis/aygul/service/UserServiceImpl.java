package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean checkUser(String login, String password) {
        User user = userRepository.findByLogin(login);
        return (user != null) && (login.equals(user.getLogin()))
                && (password.equals(user.getPassword()));
    }

    public boolean changeEmail(String login, String email) {
        return userRepository.setFixedEmailFor(email, login) != 0;
    }

    public boolean changePassword(String login, String old_password, String new_password) {
        if (checkUser(login, old_password)) {
            return userRepository.setFixedPasswordFor(new_password, login) != 0;
        } else
            return false;
    }
}
