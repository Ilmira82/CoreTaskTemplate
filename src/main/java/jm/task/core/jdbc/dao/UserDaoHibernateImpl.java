package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction;
        String s = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, " +
                "age INT NOT NULL)";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(s).addEntity(User.class);
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {

            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction;
        String s = "DROP TABLE IF EXISTS users.users";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(s).addEntity(User.class);
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {

            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {

            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {

            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction;
        List users = session.createQuery("FROM users.users").list();
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {

            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction;
        String s = "TRUNCATE TABLE users.users";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(s).addEntity(User.class);
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException e) {

            }
        }
    }
}
