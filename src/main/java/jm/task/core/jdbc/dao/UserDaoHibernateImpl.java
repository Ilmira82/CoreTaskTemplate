package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, " +
                "age INT NOT NULL)");
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("DROP TABLE IF EXISTS users.users");
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM users.users").list();
        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("TRUNCATE TABLE users.users");
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();

    }
}
