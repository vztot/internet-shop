package mate.academy.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "https://www.youtube.com/watch?v=of3i4DUVx2g");

        String dbName = "internet_shop";
        String timezone = TimeZone.getDefault().getID();
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=" + timezone;
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish connection to the DB", e);
        }
    }
}
