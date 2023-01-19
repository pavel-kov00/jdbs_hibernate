package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {

    public static void main(String[] args)  {
        // реализуйте алгоритм здесь

        UserService users = new UserServiceImpl();
        users.createUsersTable();

        users.saveUser("Bodryi","Bobrov", (byte) 17);
        users.saveUser("Taras","Bulba",(byte) 48);
        users.saveUser("Simon","Bolivar",(byte) 59);
        users.saveUser("Satoshi","Nakomato",(byte) 94);

        System.out.println(users.getAllUsers().toString());

//        users.cleanUsersTable();

//        System.out.println(users.getAllUsers());

//        users.dropUsersTable();






    }
}
