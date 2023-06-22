package com.example.springexample.repository.impl;

import com.example.springexample.config.HibernateConfig;
import com.example.springexample.entity.User;
import com.example.springexample.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query<User> query = session.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();

        }
    }

    @Override
    public User getUserByUsPw(String username, String password) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root)
                    .where(criteriaBuilder.and(criteriaBuilder.equal(root.get("username"), username), criteriaBuilder.equal(root.get("password"), password)));

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchUsers (String field, String value) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.like(root.get(field), "%"+value+"%"));

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteUser(User user) {
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
    public void manageUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public boolean thereIsAdmin() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("admin"), true));

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getResultList() != null;
        }
    }

    @Override
    public void saveNewAdmin() {
        User user = new User();
        user.setAdmin(true);
        user.setId(-18);
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setUsername("admin");
        user.setPassword("admin");
        user.setCreated(LocalDate.now());
        user.setEmail("admin@si2001.it");
        user.setBirthday(LocalDate.now());

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
