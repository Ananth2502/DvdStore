package com.ideas2it.dvdStore.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
import com.ideas2it.dvdStore.dao.AddressDao; 
import com.ideas2it.dvdStore.dao.CustomerDao;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.logger.DvdLogger;
import com.ideas2it.dvdStore.model.Address;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.sessionFactory.SessionFactoryManager;


/**
 * <p>
 * This class is used to insert the new address of particular customer, 
 * delete existing address, retrieve all address of customer details,
 * update existing address detail in the dvd store.
 *
 * @author Anantharaj
 * </p>
 */
public class AddressDaoImpl implements AddressDao {

    private StringBuilder stringBuilder = new StringBuilder();
    private DvdLogger logger = new DvdLogger();


    /**
     * @(inheritdoc)
     */
    public List<Address> getAddress(Integer customerId) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Address> addresses = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Address> criteriaQuery = 
                builder.createQuery(Address.class);
            Root<Address> root = criteriaQuery.from(Address.class);
            criteriaQuery.select(root);
                criteriaQuery.where(builder.equal(root.get
                    (DvdConstants.CUSTOMERID), customerId));
            Query query = session.createQuery(criteriaQuery);
            addresses = query.getResultList();
            return addresses;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_GET_DVDS, e);
            throw new DvdException(DvdConstants.ERROR_GET_DVDS);
        } finally {
            session.close();
        }
    }

    /**
     * @(inheritdoc)
     */
    public Address getAddress(Integer customerId, Integer addressId)
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Address> addresses = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Address> criteriaQuery = 
                builder.createQuery(Address.class);
            Root<Address> root = criteriaQuery.from(Address.class);
            criteriaQuery.select(root);
                criteriaQuery.where(builder.and(builder.equal(root.get
                    (DvdConstants.CUSTOMERID), customerId), builder.equal
                    (root.get(DvdConstants.ID), addressId)));
            Query query = session.createQuery(criteriaQuery);
            addresses = query.getResultList();
            for (Address addressDetail : addresses) {
                return addressDetail;
            }
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_GET_DVDS, e);
            throw new DvdException(DvdConstants.ERROR_GET_DVDS);
        } finally {
            session.close();
        }
        return null;
    } 

    /**
     * @(inheritdoc)
     */
    public Boolean insertAddress(Address address) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(address);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_INSERT_ORDER, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_INSERT_ADDRESS);
        } finally {
            session.close();
        }
    }


    /**
     * @(inheritdoc)
     */
    public Boolean deleteAddress(Address address) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Address completeAddress =  getAddress(address.getCustomerId(), 
                address.getId());
            session.delete(completeAddress);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_INSERT_ORDER, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_INSERT_ORDER);
        } finally {
            session.close();
        }
    }

    /**
     * @(inheritdoc)
     */
    public Boolean updateAddress(Address address) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(address);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_INSERT_ORDER, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_INSERT_ORDER);
        } finally {
            session.close();
        }
    }

}


