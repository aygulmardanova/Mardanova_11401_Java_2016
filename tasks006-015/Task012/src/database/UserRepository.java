package database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Task012
 * Mardanova Aygul
 * 11-401
 */
public class UserRepository {

    public User getUserByLogin(String login) throws SQLException, ClassNotFoundException {
        User user = null;
        Connection conn = MyConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM logins WHERE login = '" + login + "';");
        if (rs.next()) {
            user = new User(login, rs.getString("password"), rs.getString("number"));
        }
        return user;
    }

    public boolean checkPassword(String login, String password) throws SQLException, ClassNotFoundException {
        Connection conn = MyConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM logins WHERE login = '" + login + "' AND password = '" + password + "';");
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public void addUser(String login, String password, String number) throws SQLException, ClassNotFoundException {
        Connection conn = MyConnection.getConnection();
        Statement stmt = conn.createStatement();
        if (!"".equals(number)) {
            stmt.executeUpdate("INSERT INTO logins VALUES ('" + login + "', '" + password + "', '" + number + "');");
        } else {
            stmt.executeUpdate("INSERT INTO logins VALUES ('" + login + "', '" + password + "'');");
        }
    }


}
