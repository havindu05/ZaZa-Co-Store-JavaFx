package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothify", "root", "12345678");
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }


    public Connection getConnection() {

        return connection;
    }
}
