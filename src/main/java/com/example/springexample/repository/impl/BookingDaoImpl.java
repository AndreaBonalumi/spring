package com.example.springexample.repository.impl;

import com.example.springexample.config.HibernateConfig;
import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;
import com.example.springexample.repository.BookingDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAllBooking() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JPQL = "FROM Booking";
            return session.createQuery(JPQL).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAllBookingByUserId(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JPQL = "from Booking b where b.user.id = :id";
            return session.createQuery(JPQL).setParameter("id", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking getBookingById(int id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JPQL = "from Booking where id=:id";
            return (Booking) session.createQuery(JPQL).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getBookingByDate(LocalDate start, LocalDate end) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            String JPQL = "SELECT c FROM Car c WHERE NOT EXISTS (SELECT b FROM Booking b WHERE b.car = c AND b.status != 2 AND ((b.dateBookingStart BETWEEN :start AND :end) OR (b.dateBookingEnd BETWEEN :start AND :end) OR (:start BETWEEN b.dateBookingStart AND b.dateBookingEnd) OR (:end BETWEEN b.dateBookingStart AND b.dateBookingEnd)))";
            return session.createQuery(JPQL).setParameter("start", start).setParameter("end", end).getResultList();
        }
    }
    @Override
    public void manageBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(booking);
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
