package mate.academy.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class ConnectionUtil {
    private static final String DB_NAME = "internet_shop";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "https://www.youtube.com/watch?v=U4hSbh4Sww8";
    private static final String URL = "jdbc:mysql://localhost:3306/";
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
        dbProperties.put("user", USER_NAME);
        dbProperties.put("password", PASSWORD);
        String url = URL + DB_NAME
                + "?serverTimezone=" + TIMEZONE;
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish connection to the DB", e);
        }
    }
}
