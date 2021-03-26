package com.hr.hrserver.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();
    static {
        registry = new StandardServiceRegistryBuilder().configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
    }
    public static Session getSession() {
        Session session = threadLocal.get();
        if(session == null || !session.isOpen()) {
            if(null == sessionFactory) {
                buildSessionFactory();
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }
    private static void buildSessionFactory() {
        registry = new StandardServiceRegistryBuilder().configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
    }
    public static void closeSession() {
        Session session = threadLocal.get();
        threadLocal.set(null);
        if(session != null && session.isOpen()) {
            session.close();
        }
    }
}
