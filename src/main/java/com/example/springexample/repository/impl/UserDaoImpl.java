package com.example.springexample.repository.impl;

import com.example.springexample.config.HibernateConfig;
import com.example.springexample.entity.User;
import com.example.springexample.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JPQL = "FROM User";
            return session.createQuery(JPQL).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getById(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            String JPQL = "FROM User WHERE id = :id";
            return (User) session.createQuery(JPQL).setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getByUsPw(String username, String password) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JPQL = "from User where username = :username and password = :password";
            return  (User) session.createQuery(JPQL).setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchUsers (String field, String value) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JQPL = "from User where " + field + " like :value";
            return session.createQuery(JQPL).setParameter("value", "%" + value + "%").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void edit(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
