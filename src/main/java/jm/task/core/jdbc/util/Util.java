package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3306/studytestdb";
    private static String USERNAME = "root";
    private static String PASSWORD = "1234";
    private static Connection connection;

    public Util (){

    }

    public static Connection getConnection()  throws SQLException  {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return connection;
    }
}
