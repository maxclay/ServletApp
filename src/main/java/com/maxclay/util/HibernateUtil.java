package com.maxclay.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Helper class which establishes and adjusts database connection.
 *
 * @author maxclay
 */
public final class HibernateUtil {

    private static final String CONFIG_RESOURCE = "hibernate.cfg.xml";
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure(CONFIG_RESOURCE).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private HibernateUtil() {
    }

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }

}
