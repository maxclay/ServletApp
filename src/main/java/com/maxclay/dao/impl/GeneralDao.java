package com.maxclay.dao.impl;

import com.maxclay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author maxclay
 */
public class GeneralDao<T> {

    public T save(T instance) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(instance);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return instance;
    }

    public T get(Long id, Class<T> clazz) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        T instance = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            instance = session.get(clazz, id);
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return instance;
    }

    public <T> List<T> getAll(Class<T> clazz) {

        Session session = HibernateUtil.openSession();
        CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(clazz);
        query.select(query.from(clazz));
        Query<T> q = session.createQuery(query);
        List<T> instances = q.list();
        session.close();

        return instances;
    }


    public void delete(Long id, Class<T> clazz) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            T instance = session.load(clazz, id);
            session.delete(instance);
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

    public void deleteAll(Class<T> clazz) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {

            tx = session.getTransaction();
            tx.begin();

            final List<T> instances = getAll(clazz);
            for (T instance : instances) {
                session.delete(instance);
            }

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

    public boolean exists(Long id, Class<T> clazz) {

        return get(id, clazz) != null;
    }
}
