package ru.kpfu.itis.aygul.model.api;

import ru.kpfu.itis.aygul.model.User;
import ru.kpfu.itis.aygul.model.enums.Role;

/**
 * Created by aygulmardanova on 28.05.16.
 */
public class UserClient {

    private int id;

    private String login;

    private String password;

    private Role role;

    private UserClient() {}

    public UserClient (User user) {
        this(user.getId(), user.getLogin(), user.getPassword(), user.getRole());
    }

    private UserClient(int id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserClient{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
