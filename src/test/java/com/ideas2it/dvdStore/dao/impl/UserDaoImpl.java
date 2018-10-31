package com.ideas2it.dvdStore.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.UserDao;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.logger.DvdLogger;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.sessionFactory.SessionFactoryManager;


/**
 * <p>
 * This class is used to check user already exists or not dvd store 
 *
 * @author Anantharaj
 * </p>
 */
public class UserDaoImpl implements UserDao {

    private StringBuilder stringBuilder = new StringBuilder();
    private DvdLogger logger = new DvdLogger();

    /** 
     * @(inheritdoc)
     */
    public User userLogin(User user) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.USERID), user.getUserId()), builder.equal(root.get
                (DvdConstants.PASSWORD), user.getPassword()), 
                builder.equal(root.get(DvdConstants.ROLE), user.getRole())));
            Query query = session.createQuery(criteriaQuery);
            List<User> userCollection = query.getResultList();
            for (User customer : userCollection) {
                return customer;
            }
        } catch (HibernateException e) {
            logger.error(DvdConstants.MSG_SERVER_ERROR, e);
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
        return null;
    }

}

