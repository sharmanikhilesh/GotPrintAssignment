package org.gotprint.assignment.usernotes.dbservice;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

    public static SessionFactory buildSessionFactory() {
      try {
    	  Configuration configuration = new Configuration().configure();
    	  ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
    			  applySettings(configuration.getProperties()).buildServiceRegistry();

        return configuration.buildSessionFactory(serviceRegistry);
      }
      catch (Throwable ex) {
        System.err.println("Initial SessionFactory creation failed." + ex);
        throw new ExceptionInInitializerError(ex);
      }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
