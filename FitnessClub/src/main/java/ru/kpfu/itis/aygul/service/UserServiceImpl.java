package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.repository.ProbablyInstructorRepository;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProbablyInstructorService probablyInstructorService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    @Override
    public boolean checkUser(String login, String password) {
        User user = userRepository.findByLogin(login);
        return (user != null) && (login.equals(user.getLogin()))
                && (password.equals(user.getPassword()));
    }

    @Override
    public void changeRole(Role role, int user_id) {
        userRepository.setFixedRoleFor(role, user_id);
    }

    @Override
    public boolean changeEmail(String login, String email) {
        return userRepository.setFixedEmailFor(email, login) != 0;
    }

    @Override
    public boolean changePassword(String login, String old_password, String new_password) {
        return checkUser(login, old_password) &&
                userRepository.setFixedPasswordFor(new_password, login) != 0;
    }

    @Transactional
    @Override
    public void saveUser(String login, String password, String email, String name,
                         String surname, String photo, String phone_number, Role role) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPhoto(photo);
        user.setPhoneNumber(phone_number);
        user.setRole(role);
        userRepository.save(user);
        //FIXME: delete the commenting
        /*if (role.equals(Role.ROLE_INSTRUCTOR)) {
            probablyInstructorService.addProbablyInstructor(user);
        }*/
    }
}
