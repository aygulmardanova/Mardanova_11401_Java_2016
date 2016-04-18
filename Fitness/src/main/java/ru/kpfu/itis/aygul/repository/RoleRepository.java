package ru.kpfu.itis.aygul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.aygul.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findById(int id);

    List<Role> findAll();

    Role findByRole(String role);
}
