package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute("create table if not exists users " +
                    "(   id       bigint(20) not null auto_increment ," +
                    "    name     varchar(40) null,"   +
                    "    lastname varchar(40) null,"   +
                    "    age      tinyint     null,"   +
                    "    primary key(id)           "   +
                    ") ;");

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
        }

    }

    public void dropUsersTable() {

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute("drop table if exists users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("insert into users (name, lastname, age) values (" + "\'" + name +"\', \'" + lastName +"\', "+ age +")");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("delete from users where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Connection connection = new Util().getConnection();
            Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {

        try(Connection connection = new Util().getConnection();
            Statement statement = connection.createStatement();) {
            statement.executeUpdate("delete from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
