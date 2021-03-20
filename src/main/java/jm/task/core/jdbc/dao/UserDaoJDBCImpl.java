package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String s = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, " +
                "age INT NOT NULL)";


        try {
            connection = Util.getconnect();
            statement = connection.createStatement();
            statement.execute(s);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        Statement statement = null;
        String s = "DROP TABLE users.users";

        try {
            connection = Util.getconnect();
            statement = connection.createStatement();
            statement.execute(s);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        PreparedStatement statement = null;
        String s = "INSERT INTO users.users" + "(name, lastName, age)" + "VALUES" + "(?,?,?)";

        try {
            connection = Util.getconnect();
            statement = connection.prepareStatement(s);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String s = "DELETE FROM users.users WHERE `id` = ?";
        try {
            connection = Util.getconnect();
            statement = connection.prepareStatement(s);
            statement.setLong(1, id);
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = null;
        Statement statement = null;
        List users = new ArrayList<>();
        String s = "SELECT * FROM users.users";
        try {
            connection = Util.getconnect();
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(s);
            while (res.next()) {
                String name = res.getString("name");
                String lasrName = res.getString("lastNAme");
                Byte age = res.getByte("age");
                User user = new User(name, lasrName, age);
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }

            return users;
        }
    }

    public void cleanUsersTable() {
        Connection connection = null;
        Statement statement = null;
        String s = "TRUNCATE TABLE users.users";
        try {
            connection = Util.getconnect();
            statement = connection.createStatement();
            statement.execute(s);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }

    }
}
