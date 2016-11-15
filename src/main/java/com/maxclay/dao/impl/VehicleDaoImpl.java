package com.maxclay.dao.impl;

import com.maxclay.dao.VehicleDao;
import com.maxclay.model.Vehicle;
import com.maxclay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * TODO review
 *
 * @author maxclay
 */
public class VehicleDaoImpl implements VehicleDao {

    public Vehicle save(Vehicle vehicle) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(vehicle);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return vehicle;
    }

    public Vehicle get(Long id) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Vehicle vehicle = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            vehicle = session.get(Vehicle.class, id);
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return vehicle;
    }

    public List<Vehicle> getAll() {

        Session session = HibernateUtil.openSession();
        CriteriaQuery<Vehicle> query = session.getCriteriaBuilder().createQuery(Vehicle.class);
        query.select(query.from(Vehicle.class));
        Query<Vehicle> q = session.createQuery(query);
        List<Vehicle> vehicles = q.list();
        session.close();

        return vehicles;
    }


    public void delete(Long id) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            Vehicle vehicle = session.load(Vehicle.class, id);
            session.delete(vehicle);
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public boolean exists(Long id) {

        Session session = HibernateUtil.openSession();
        Vehicle vehicle = session.get(Vehicle.class, id);
        session.close();

        return vehicle != null;
    }

}
