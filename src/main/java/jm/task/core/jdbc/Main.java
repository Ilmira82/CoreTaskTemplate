package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.removeUserById(1);
        userService.getAllUsers().forEach(System.out::println);
        userService.dropUsersTable();


        //User userOne = new User("Вася", "Васильев", (byte) 20);
        //userService.saveUser(userOne.getName(), userOne.getLastName(), userOne.getAge());

        //User userTwo = new User("Петя", "Петров", (byte) 21);
        //userService.saveUser(userTwo.getName(), userTwo.getLastName(), userTwo.getAge());

        // User userThree = new User("Иван", "Иванов", (byte) 22);
        //userService.saveUser(userThree.getName(), userThree.getLastName(), userThree.getAge());

        //User userFour = new User("Илья", "Сидоров", (byte) 20);
        //userService.saveUser(userFour.getName(), userFour.getLastName(), userFour.getAge());

        //userService.getAllUsers().forEach(System.out::println);
        // userService.cleanUsersTable();
        // userService.dropUsersTable();

    }

}
