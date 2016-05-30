package ru.kpfu.itis.aygul.model;

import ru.kpfu.itis.aygul.model.enums.Role;

/**
 * Simplified class for User model
 */
public class UserClient {

    private Integer id;
    private String login;
    private String password;
    private Role role;

    public UserClient() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserClient{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
