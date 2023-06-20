package com.example.springexample.repository.impl;

import com.example.springexample.config.HibernateConfig;
import com.example.springexample.entity.Car;
import com.example.springexample.repository.CarDao;
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
public class CarDaoImpl implements CarDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAllCar() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            /*String JPQL = "FROM Car";
            return session.createQuery(JPQL).getResultList();*/
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);
            criteriaQuery.select(root);
            Query<Car> query = session.createQuery(criteriaQuery);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> getCarByDate(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public Car getCarById(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            /*String JPQL = "FROM Car WHERE id = :id";
            return (Car) session.createQuery(JPQL).setParameter("id", id).getSingleResult();*/
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);
            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("id"), id));

            Query<Car> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void manageCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
