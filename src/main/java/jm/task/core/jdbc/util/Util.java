package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public static SessionFactory getSesstion () {
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/studytestdb");
        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "1234");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        SessionFactory sessionFactory = new Configuration().addProperties(prop).buildSessionFactory();

        return sessionFactory;
    }

}
