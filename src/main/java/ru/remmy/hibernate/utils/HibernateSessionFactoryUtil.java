package ru.remmy.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.entities.*;

public class HibernateSessionFactoryUtil {


    private static final SessionFactory sessionFactory = createSessionFactory();

    public static SessionFactory createSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Task.class);
            SessionFactory newSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return newSessionFactory;
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
