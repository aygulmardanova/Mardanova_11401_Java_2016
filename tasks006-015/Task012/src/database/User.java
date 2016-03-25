package database;

/**
 * Task012
 * Mardanova Aygul
 * 11-401
 */
public class User {

    private String login;
    private String password;
    private String number;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String number) {
        this.login = login;
        this.password = password;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
