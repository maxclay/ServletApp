package com.maxclay.dao.impl;

import com.maxclay.dao.UserDao;
import com.maxclay.model.User;
import com.maxclay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * @author maxclay
 */
public class UserDaoImpl implements UserDao {

    public User save(User user) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(user);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public User get(Long id) {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;
        try {

            tx = session.getTransaction();
            tx.begin();
            user = session.get(User.class, id);
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public Optional<User> getByEmail(String email) {

//        Session session = HibernateUtil.openSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> personRoot = criteriaQuery.from(User.class);
//        criteriaQuery.select(personRoot);
//        criteriaQuery.where(criteriaBuilder.equal(personRoot.get(User_.email), email));
//        Optional<User> userOptional = session.createQuery(criteriaQuery).uniqueResultOptional();
//        session.close();

        return null;
    }

}
