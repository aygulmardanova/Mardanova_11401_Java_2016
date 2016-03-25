package database;

/**
 * Task012
 * Mardanova Aygul
 * 11-401
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MyConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/LoginDatabase",
                "postgres",
                "salma0312"
        );
        return connection;
    }
}

