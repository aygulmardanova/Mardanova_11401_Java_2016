package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.Role;
import ru.kpfu.itis.aygul.repository.RoleRepository;
import ru.kpfu.itis.aygul.service.interfaces.RoleService;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(int id) {
        return roleRepository.findById(id);
    }

    public Role getRole(String role) {
        return roleRepository.findByRole(role);
    }

}
