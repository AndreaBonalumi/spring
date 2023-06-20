package com.example.springexample.repository.impl;

import com.example.springexample.config.HibernateConfig;
import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;
import com.example.springexample.entity.User;
import com.example.springexample.repository.BookingDao;
import com.example.springexample.service.UserService;
import com.example.springexample.service.impl.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingDaoImpl implements BookingDao {
    UserService userService = new UserServiceImpl();
    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAllBooking() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
            Root<Booking> root = criteriaQuery.from(Booking.class);
            criteriaQuery.select(root);
            Query<Booking> query = session.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAllBookingByUserId(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            User user = userService.getUserById(id);
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
            Root<Booking> root = criteriaQuery.from(Booking.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));

            Query<Booking> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking getBookingById(int id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
            Root<Booking> root = criteriaQuery.from(Booking.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            Query<Booking> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getBookingByDate(LocalDate start, LocalDate end) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            /*String JPQL = "SELECT c FROM Car c WHERE NOT EXISTS (SELECT b FROM Booking b WHERE b.car = c AND b.status != 2 AND ((b.dateBookingStart BETWEEN :start AND :end) OR (b.dateBookingEnd BETWEEN :start AND :end) OR (:start BETWEEN b.dateBookingStart AND b.dateBookingEnd) OR (:end BETWEEN b.dateBookingStart AND b.dateBookingEnd)))";
            return session.createQuery(JPQL).setParameter("start", start).setParameter("end", end).getResultList();*/

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);
            criteriaQuery.select(root);

            Subquery<Booking> subquery = criteriaQuery.subquery(Booking.class);
            Root<Booking> subqueryRoot = subquery.from(Booking.class);
            subquery.select(subqueryRoot.get("car"));
            subquery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(subqueryRoot.get("car"), root),
                    criteriaBuilder.notEqual(subqueryRoot.get("status"), 2),
                    criteriaBuilder.or(
                            criteriaBuilder.between(subqueryRoot.get("dateBookingStart"), start, end),
                            criteriaBuilder.between(subqueryRoot.get("dateBookingEnd"), start, end),
                            criteriaBuilder.and(
                                    criteriaBuilder.lessThan(subqueryRoot.get("dateBookingStart"), end),
                                    criteriaBuilder.not(criteriaBuilder.lessThan(subqueryRoot.get("dateBookingStart"), start))
                            ),
                            criteriaBuilder.and(
                                    criteriaBuilder.lessThan(subqueryRoot.get("dateBookingEnd"), end),
                                    criteriaBuilder.not(criteriaBuilder.lessThan(subqueryRoot.get("dateBookingEnd"), start))
                            )

                    )
            ));

            criteriaQuery.where(criteriaBuilder.not(criteriaBuilder.exists(subquery)));

            Query<Car> query = session.createQuery(criteriaQuery);

            return query.getResultList();

        }
    }
    @Override
    public void manageBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(booking);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(Booking booking) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(booking);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
