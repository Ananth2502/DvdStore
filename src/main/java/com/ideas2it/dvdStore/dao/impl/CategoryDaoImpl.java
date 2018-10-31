package com.ideas2it.dvdStore.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.CategoryDao;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.logger.DvdLogger;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.sessionFactory.SessionFactoryManager;

/**
 * <p>
 * This class is used to insert the new Category, delete existing Category,
 * retrieve all available Category details, update existing Category detail.
 *
 * @author Anantharaj
 * </p>
 */
public class CategoryDaoImpl implements CategoryDao {

    private StringBuilder stringBuilder = new StringBuilder();
    private DvdLogger logger = new DvdLogger();

    /** 
     * @(inheritdoc)
     */
    public Boolean insertCategory(Category category) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            category.setStatus(Boolean.TRUE);
            session.save(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
             logger.error
                (DvdConstants.ERROR_INSERT_CATEGORY + category.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_INSERT_CATEGORY + category.getName());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Category> getCategories(Boolean status) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Set<Category> categories;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(DvdConstants.STATUS),
                status));
            Filter filter = session.enableFilter(DvdConstants.LABEL_DVDFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Category> categoryCollection = query.getResultList();
            categories = new LinkedHashSet<Category>(categoryCollection);
            transaction.commit();
            return categories;
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_GET_CATEGORIES, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_GET_CATEGORIES);
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Category getDvdsByCategory(Integer id) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Category category = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root
                .get(DvdConstants.ID), id), builder.equal(root
                .get(DvdConstants.STATUS), Boolean.TRUE)));
            Filter filter = session.enableFilter(DvdConstants.LABEL_DVDFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Category>categoryCollection = query.getResultList();
            Set<Category> categories = new 
                LinkedHashSet<Category>(categoryCollection);
            for (Category dvdCategory : categories) {
                category = dvdCategory	;
            }
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_GET_DVD_BY_CATEGORY +id, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.ERROR_GET_DVD_BY_CATEGORY +id);
        } finally {
            session.close();
        }
        return category;
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteCategory(Category category) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            category.setStatus(Boolean.FALSE);
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error
                (DvdConstants.ERROR_DELETE_CATEGORY + category.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_DELETE_CATEGORY + category.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Category getCategory(Integer id, Boolean status)throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root
                .get(DvdConstants.ID), id), builder.equal(root
                .get(DvdConstants.STATUS), status)));
            Filter filter = session.enableFilter(DvdConstants.LABEL_DVDFILTER);
            filter.setParameter(DvdConstants.STATUS, Boolean.TRUE);
            Query query = session.createQuery(criteriaQuery);
            List<Category> categoryCollection = query.getResultList();
            transaction.commit();
            for (Category category : categoryCollection) {
                return category;
            }
            return null;
        } catch (HibernateException e) {
            logger.error(DvdConstants.MSG_SERVER_ERROR, e);
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
    public Boolean updateCategory(Category category) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            category.setStatus(Boolean.TRUE);
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error
                (DvdConstants.ERROR_UPDATE_CATEGORY + category.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_UPDATE_CATEGORY + category.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean restoreCategory(Category category) throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (0 == category.getId()) {
               category = getCategoryByName(category.getName(),Boolean.FALSE);
            }
            category.setStatus(Boolean.TRUE);
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error
                (DvdConstants.ERROR_RESTORE_CATEGORY + category.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException
                (DvdConstants.ERROR_RESTORE_CATEGORY+ category.getId());
        } finally {
            session.close();
        }
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean isCategoryExists(Category category, Boolean status)
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root
                .get(DvdConstants.NAME), category.getName()),builder.equal(root
                .get(DvdConstants.STATUS), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Category> categoryCollection = query.getResultList();
            transaction.commit();
            if (categoryCollection.isEmpty()) {
               return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (HibernateException e) {
            logger.error(DvdConstants.MSG_SERVER_ERROR, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
    }

    /** 
     * This method is used to get the category in inactive status by 
     * category name
     */
    public Category getCategoryByName(String name, Boolean status) 
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                builder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root
                .get(DvdConstants.NAME), name), builder.equal(root
                .get(DvdConstants.STATUS), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Category> categoryCollection = query.getResultList();
            transaction.commit();
            for (Category category : categoryCollection) {
                return category;
            }
            return null;
        } catch (HibernateException e) {
            logger.error(DvdConstants.MSG_SERVER_ERROR, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new DvdException(DvdConstants.MSG_SERVER_ERROR);
        } finally {
            session.close();
        }
    }

}
