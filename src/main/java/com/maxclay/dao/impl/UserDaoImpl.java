package com.maxclay.dao.impl;

import com.maxclay.dao.UserDao;
import com.maxclay.model.User;
import com.maxclay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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

    // TODO get rid of deprecated method invocation
    public User getByEmail(String email) {

        Session session = HibernateUtil.openSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();

        return user;
    }

}
