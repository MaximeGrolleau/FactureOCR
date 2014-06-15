package fr.utbm.gl52.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Property;
import org.hibernate.service.ServiceRegistry;
 
/**
 * @author mgrollea
 *
 */
public class HibernateUtil {
 
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    /**
     * 
     */
	private static SessionFactory buildSessionFactory() {
        try {
        	 Configuration configuration = new Configuration();
             configuration.configure();
             serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                     configuration.getProperties()).build();
             sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex); //$NON-NLS-1$
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    /**
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * 
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
 
}