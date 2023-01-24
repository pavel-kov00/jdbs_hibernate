package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement();) {

            connection.setAutoCommit(false);
            statement.execute("create table if not exists users " +
                    "(   id       bigint(20) not null auto_increment ," +
                    "    name     varchar(40) null,"   +
                    "    lastname varchar(40) null,"   +
                    "    age      tinyint     null,"   +
                    "    primary key(id)           "   +
                    ") ;");

            connection.commit();
            //******** mysql *************
            //  CREATE TABLE `test-first`.`new_table` (
            //  `id` INT NOT NULL AUTO_INCREMENT,
            //  `name` VARCHAR(45) NULL,
            //  `lastname` VARCHAR(45) NULL,
            //  PRIMARY KEY (`id`))
            //  ENGINE = InnoDB
            //  DEFAULT CHARACTER SET = utf16;

            //******* idea generation code  *******
            //    create table if not exists db_name (
            //    id   bigint auto_increment,
            //    name varchar(45) null,
            //    constraint id,
            //    primary key (id)  );

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement();) {
            connection.setAutoCommit(false);
            statement.execute("drop table if exists users");

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement prstatement = connection.prepareStatement(
                     "insert into users (name, lastname, age) values( ?, ?, ?)");) {
            connection.setAutoCommit(false);
            prstatement.setString(1,name);
            prstatement.setString(2,lastName);
            prstatement.setByte(3, age);
            prstatement.executeUpdate();
            connection.commit();

            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement();) {
            connection.setAutoCommit(false);
            statement.executeUpdate("delete from users where id=" + id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return users;
    }

    public void cleanUsersTable() {

        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Statement statement = connection.createStatement();) {
            connection.setAutoCommit(false);
            statement.executeUpdate("delete from users");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
