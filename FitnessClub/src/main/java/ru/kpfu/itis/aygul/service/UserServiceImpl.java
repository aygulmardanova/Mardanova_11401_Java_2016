package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;
import ru.kpfu.itis.aygul.repository.InstructorRepository;
import ru.kpfu.itis.aygul.repository.ProbablyInstructorRepository;
import ru.kpfu.itis.aygul.repository.UserRepository;
import ru.kpfu.itis.aygul.service.interfaces.ProbablyInstructorService;
import ru.kpfu.itis.aygul.service.interfaces.UserService;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProbablyInstructorService probablyInstructorService;

    @Autowired
    InstructorRepository instructorRepository;

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
    public boolean checkUser(int id, String password) {
        User user = userRepository.findById(id);
        return (user != null) && (id == user.getId())
                && (password.equals(user.getPassword()));    }

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
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        if (role.equals(Role.ROLE_INSTRUCTOR)) {
            probablyInstructorService.addProbablyInstructor(user);
        }
    }

    @Override
    public void updateUser(int user_id, String photo, String login, String name,
                           String surname, String email, String new_password,
                           String new_password_repeat, String phoneNumber) {
        User user = getUserById(user_id);
        if (photo != null && !photo.equals("")) {
            user.setPhoto(photo);
        }
        if (login != null && !login.equals("")) {
            user.setLogin(login);
        }
        if (name != null && !name.equals("")) {
            user.setName(name);
        }
        if (surname != null && !surname.equals("")) {
            user.setSurname(surname);
        }
        if (email != null && !email.equals("")) {
            user.setEmail(email);
        }
        if (phoneNumber != null && !phoneNumber.equals("")) {
            user.setPhoneNumber(phoneNumber);
        }
        if (new_password_repeat != null && new_password.equals("new_password_repeat")) {
            user.setPassword(new_password);
        }
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsersWhoAreInstructors() {
        return userRepository.findAllByRole(Role.ROLE_INSTRUCTOR);
    }

    @Override
    public List<User> getAllInstructorsSortBy(String sort) {
        Role instr = Role.ROLE_INSTRUCTOR;
        if (sort.equals("name,asc")) {
            return userRepository.findAllByRoleOrderByNameAsc(instr);
        } else if (sort.equals("name,desc")) {
            return userRepository.findAllByRoleOrderByNameDesc(instr);
        } else if (sort.equals("surn,asc")) {
            return userRepository.findAllByRoleOrderBySurnameAsc(instr);
        } else if (sort.equals("surn,desc")) {
            return userRepository.findAllByRoleOrderBySurnameDesc(instr);
        }
        return null;
    }

    @Override
    public List<User> getAllInstructorsSortByNameAsc() {
        return userRepository.findAllByRoleOrderByNameAsc(Role.ROLE_INSTRUCTOR);
    }

    @Override
    public List<User> getAllInstructorsSortByNameDesc() {
        return userRepository.findAllByRoleOrderByNameDesc(Role.ROLE_INSTRUCTOR);
    }

    @Override
    public List<User> getAllInstructorsSortBySurnameAsc() {
        return userRepository.findAllByRoleOrderBySurnameAsc(Role.ROLE_INSTRUCTOR);
    }

    @Override
    public List<User> getAllInstructorsSortBySurnameDesc() {
        return userRepository.findAllByRoleOrderBySurnameDesc(Role.ROLE_INSTRUCTOR);
    }
}
