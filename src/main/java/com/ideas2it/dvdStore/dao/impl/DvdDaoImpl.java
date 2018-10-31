package com.ideas2it.dvdStore.dao.impl;

import java.util.ArrayList;
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
import com.ideas2it.dvdStore.dao.DvdDao;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.logger.DvdLogger;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
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
public class DvdDaoImpl implements DvdDao {

    private StringBuilder stringBuilder = new StringBuilder();
    private DvdLogger logger = new DvdLogger();

    /**
     * @(inheritdoc)
     */
    public Boolean insertDvd(Dvd dvd) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dvd.setStatus(Boolean.TRUE);
            session.saveOrUpdate(dvd);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_INSERT_DVD + dvd.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new 
                DvdException(DvdConstants.ERROR_INSERT_DVD + dvd.getName());
        } finally {
            session.close();
        }
    }

    /**
     * @(inheritdoc)
     */
    public Set<Dvd> getDvds(Boolean status) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Set<Dvd> dvds = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(DvdConstants.STATUS),
                status));
            Filter filter = session.enableFilter
                (DvdConstants.LABEL_CATEGORYFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Dvd> dvdCollection = query.getResultList();
            dvds = new LinkedHashSet<Dvd>(dvdCollection);
            return dvds;
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
    public Dvd getDvdById(Integer id, Boolean status) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.ID), id), builder.equal(root.get
                (DvdConstants.STATUS), status)));
            Filter filter = session.enableFilter
                (DvdConstants.LABEL_CATEGORYFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Dvd> dvdCollection = query.getResultList();
            transaction.commit();
            for (Dvd dvd : dvdCollection) {
                return dvd;
            }
            return null;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_UPDATE_DVD + id, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_UPDATE_DVD + id);
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Dvd> getDvdsByIds(Set<Dvd> dvds, Boolean status) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            List<Integer> list = new ArrayList<Integer>();
            for (Dvd dvd : dvds) { 
                 list.add(dvd.getId());
            } 
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and((root.get(DvdConstants.ID)
                .in(list)), builder.equal(root.get(DvdConstants.STATUS),
                status)));
            Filter filter = session.enableFilter
                (DvdConstants.LABEL_CATEGORYFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Dvd> dvdCollection = query.getResultList();
            Set<Dvd> dvdss = new LinkedHashSet<Dvd>(dvdCollection);
            transaction.commit();
            return dvdss;
        } catch (HibernateException e) {
            logger.error("server error", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Dvd> getDvdsByName(String name) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Set<Dvd> dvds = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.NAME), name), builder.equal(root.get
                (DvdConstants.STATUS), Boolean.TRUE)));
            Filter filter = session.enableFilter
                (DvdConstants.LABEL_CATEGORYFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Dvd> dvdCollection = query.getResultList();
            dvds = new LinkedHashSet<Dvd>(dvdCollection);
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_GET_DVD + name, e);
            throw new DvdException(DvdConstants.ERROR_GET_DVD + name);
        } finally {
            session.close();
        }
        return dvds;
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteDvd(Dvd dvd) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dvd.setStatus(Boolean.FALSE);
            session.update(dvd);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_DELETE_DVD + dvd.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_DELETE_DVD + dvd.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean updateDvd(Dvd dvd) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dvd.setStatus(Boolean.TRUE);
            session.update(dvd);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_UPDATE_DVD + dvd.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_UPDATE_DVD + dvd.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean restoreDvd(Dvd dvd) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dvd.setStatus(Boolean.TRUE);
            session.update(dvd);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_RESTORE_DVD + dvd.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_RESTORE_DVD+ dvd.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean reinsertDvd(Dvd dvd) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Dvd inactivedvd = getInactiveDvdByName(dvd);
            inactivedvd.setQuantity(dvd.getQuantity());
            inactivedvd.setPrice(dvd.getPrice());
            inactivedvd.setRating(dvd.getRating());
            inactivedvd.setCategories(dvd.getCategories());
            inactivedvd.setStatus(Boolean.TRUE);
            session.update(inactivedvd);
            transaction.commit();
            return true; 
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_INSERT_DVD + dvd.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new 
                DvdException(DvdConstants.ERROR_INSERT_DVD + dvd.getName());
        } finally {
            session.close();
        }
    }

    /** 
     * This method is used to get the dvd in inactive status based on dvd name,
     * dvd language, dvd realesdete.
     */
    public Dvd getInactiveDvdByName(Dvd dvd) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.NAME), dvd.getName()),builder.equal(root.get
                (DvdConstants.LANGUAGE),dvd.getLanguage()),builder.equal
                (root.get(DvdConstants.RELEASE_DATE), dvd.getReleaseDate()), 
                builder.equal(root.get(DvdConstants.STATUS), Boolean.FALSE)));
            Filter filter = session.enableFilter
                (DvdConstants.LABEL_CATEGORYFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Dvd> dvdCollection = query.getResultList();
            transaction.commit();
            for (Dvd inactiveDvd : dvdCollection) {
                return inactiveDvd;
            }
        } catch (HibernateException e) {
            logger.error(DvdConstants.MSG_SERVER_ERROR, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
        return null;
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean isDvdExists(Dvd dvd, Boolean status) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = builder.createQuery(Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.NAME), dvd.getName()), builder.equal(root.get
                (DvdConstants.LANGUAGE), dvd.getLanguage()), builder.equal
                (root.get(DvdConstants.RELEASE_DATE),dvd.getReleaseDate()), 
                builder.equal(root.get(DvdConstants.STATUS),status)));
            Query query = session.createQuery(criteriaQuery);
            List<Dvd> dvdCollection = query.getResultList();
            if (dvdCollection.isEmpty()) {
               return false;
            }
            return true;
        } catch (HibernateException e) {
            logger.error(DvdConstants.MSG_SERVER_ERROR, e);
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
    }
}
