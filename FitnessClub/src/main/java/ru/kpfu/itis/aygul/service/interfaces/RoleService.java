package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.Role;

import java.util.List;

/**
 * Created by Айгуль on 18.04.2016.
 */
public interface RoleService {

    List<Role> getRoles();

    Role getRole(int id);

    Role getRole(String role);


}
