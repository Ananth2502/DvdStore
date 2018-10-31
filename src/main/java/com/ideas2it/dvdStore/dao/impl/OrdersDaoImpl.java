package com.ideas2it.dvdStore.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.OrdersDao;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.logger.DvdLogger;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.Orders;
import com.ideas2it.dvdStore.sessionFactory.SessionFactoryManager;

/**
 * <p>
 * OrdersServiceImpl class is contains the operations of the orders
 * such as Insert new ordrer to dvd store, delete existing order details
 * in dvd store, viewing the ordered details...
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public class OrdersDaoImpl implements OrdersDao {

    private StringBuilder stringBuilder = new StringBuilder();
    private DvdLogger logger = new DvdLogger();

    /**
     * @(inheritdoc)
     */
    public Boolean insertOrder(Orders orders) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(orders);
            Set<Dvd> dvds = orders.getDvds();
            for (Dvd dvd : dvds) {
                dvd.setQuantity(dvd.getQuantity() - 1);
                session.update(dvd);
            }
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
    public Set<Orders> getOrders(Integer customerId) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Set<Orders> orders = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Orders> criteriaQuery = 
                builder.createQuery(Orders.class);
            Root<Orders> root = criteriaQuery.from(Orders.class);
            criteriaQuery.select(root);
            if (0 != customerId) {
                criteriaQuery.where(builder.equal(root.get
                    (DvdConstants.CUSTOMER), customerId));
            }
            Query query = session.createQuery(criteriaQuery);
            List<Orders> orderCollection = query.getResultList();
            orders = new LinkedHashSet<Orders>(orderCollection);
            return orders;
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
    public Boolean orderExists(Integer customerId, Integer addressId) 
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Orders> criteriaQuery = 
                builder.createQuery(Orders.class);
            Root<Orders> root = criteriaQuery.from(Orders.class);
            criteriaQuery.select(root);
                criteriaQuery.where(builder.and(builder.equal(root.get
                    (DvdConstants.CUSTOMER), customerId),builder.equal(root.get
                    (DvdConstants.ADDRESS), addressId)));
            Query query = session.createQuery(criteriaQuery);
            List<Orders> orderCollection = query.getResultList();
            if (orderCollection.size() > 0) {
               return true;
            }
            return false;
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
    public Orders getOrder(Integer orderId) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Orders> criteriaQuery = 
                builder.createQuery(Orders.class);
            Root<Orders> root = criteriaQuery.from(Orders.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get
                (DvdConstants.ID), orderId));
            Query query = session.createQuery(criteriaQuery);
            List<Orders> orderCollection = query.getResultList();
            Set<Orders> orders = new LinkedHashSet<Orders>(orderCollection);
            for (Orders order : orders) {
                return order;
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
    public Boolean deleteOrder(Orders orders) 
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(orders);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_DELETE_DVD + orders.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_DELETE_DVD + orders.getId());
        } finally {
            session.close();
        }
    }





}
