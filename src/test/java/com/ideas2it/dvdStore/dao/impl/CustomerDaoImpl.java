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
import com.ideas2it.dvdStore.dao.CustomerDao;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.logger.DvdLogger;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.sessionFactory.SessionFactoryManager;


/**
 * <p>
 * This class is used to insert the new dvd to dvd store, 
 * delete existing dvd in the dvd store, retrieve all available dvd details,
 * update existing dvd detail in the dvd store.
 *
 * @author Anantharaj
 * </p>
 */
public class CustomerDaoImpl implements CustomerDao {

    private StringBuilder stringBuilder = new StringBuilder();
    private DvdLogger logger = new DvdLogger();

    /**
     * @(inheritdoc)
     */
    public Boolean insertCustomer(Customer customer) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer.setStatus(Boolean.TRUE);
            session.save(customer);
            User user = customer.getUser();
            user.setCustomerId(customer.getId());
            session.save(user);    
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error
                (DvdConstants.ERROR_INSERT_CUSTOMER + customer.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_INSERT_CUSTOMER + customer.getName());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Customer getCustomerByMobile(String mobile, Boolean status) 
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.MOBILE), mobile), builder.equal(root.get
                (DvdConstants.STATUS), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_SERACH_CUSTOMER, e);
            throw new DvdException(DvdConstants.ERROR_SERACH_CUSTOMER);
        } finally {
            session.close();
        }
        return null;
    }

    /** 
     * @(inheritdoc)
     */
    public Customer getCustomerById(Integer id, Boolean status) 
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.ID), id), builder.equal(root.get
                (DvdConstants.STATUS), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
            return null;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_UPDATE_DVD, e);
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteCustomer(Customer customer) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer.setStatus(Boolean.FALSE);
            session.update(customer);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error
                (DvdConstants.ERROR_DELETE_CUSTOMER + customer.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_DELETE_CUSTOMER + customer.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean updateCustomer(Customer customer) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error
                (DvdConstants.ERROR_UPDATE_CUSTOMER + customer.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_UPDATE_CUSTOMER + customer.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean restoreCustomer(Customer customer) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer.setStatus(Boolean.TRUE);
            session.update(customer);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_RESTORE_CUSTOMER 
                + customer.getMobileNumber(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_RESTORE_CUSTOMER 
                + customer.getMobileNumber());
        } finally {
            session.close();
        }
    }

    /**
     * @(inheritdoc)
     */
    public Set<Customer> getCustomers(Boolean status) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Set<Customer> customers;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(DvdConstants.STATUS),
                status));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            customers = new LinkedHashSet<Customer>(customerCollection);
            transaction.commit();
            return customers;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_GET_CUSTOMERS, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_GET_CUSTOMERS);
        } finally {
            session.close();
        }
    }

}
