package task013;

/**
 * Task013
 * Mardanova Aygul
 * 11-401
 */
public class User {

    private String name;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    User(String name) {
        this.name = name;
    }

    User(String name, String email) {
        this(name);
        setEmail(email);
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("Address has been changed: " + this.email);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
