package com.ideas2it.dvdStore.sessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * <p>
 * This class is a singleton class,  which is used create sessionfactory object
 * only once, if we try to create another object it returns the existing 
 * object only. 
 * it is used to configure the hibernate configuration file.
 *
 * @author Anantharaj
 * </p>
 */
public class SessionFactoryManager {

    private static SessionFactory factory;
    private static SessionFactoryManager sessionFactory;
    
    private SessionFactoryManager() {}

    /**
     * Used to create session factory object only once
     */
    public static SessionFactoryManager getInstance() {
        if (null == sessionFactory ) {
            sessionFactory = new SessionFactoryManager();
        }
        return sessionFactory;
    }


    /**
     * Used to build the session factory based on hibernate configuration file
     */
    public static SessionFactory getSessionFactory() {    
        if (null == factory) {
            factory = new Configuration()
                      .configure("com/ideas2it/dvdStore/xml/hibernate.cfg.xml")
                      .buildSessionFactory();
        }
        return factory;
    }   

}
