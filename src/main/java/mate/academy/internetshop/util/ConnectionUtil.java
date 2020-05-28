package mate.academy.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class ConnectionUtil {
    private static final String DB_NAME = "internet_shop";
    private static final String TIMEZONE = TimeZone.getDefault().getID();

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
        dbProperties.put("password",
                "2164092");
        String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?serverTimezone=" + TIMEZONE;
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish connection to the DB", e);
        }
    }
}
