package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDao u = new UserDaoJDBCImpl();
        u.createUsersTable();

        User userOne = new User("Вася", "Васильев", (byte) 20);
        u.saveUser(userOne.getName(), userOne.getLastName(), userOne.getAge());

        User userTwo = new User("Петя", "Петров", (byte) 21);
        u.saveUser(userTwo.getName(), userTwo.getLastName(), userTwo.getAge());

        User userThree = new User("Иван", "Иванов", (byte) 22);
        u.saveUser(userThree.getName(), userThree.getLastName(), userThree.getAge());

        User userFour = new User("Илья", "Сидоров", (byte) 20);
        u.saveUser(userFour.getName(), userFour.getLastName(), userFour.getAge());

        u.getAllUsers().forEach(System.out::println);
        u.cleanUsersTable();
        u.dropUsersTable();
    }

}
