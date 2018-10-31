package com.ideas2it.dvdStore.service.impl; 

import java.util.Set;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.DvdDao;
import com.ideas2it.dvdStore.dao.impl.DvdDaoImpl;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.service.CategoryService; 
import com.ideas2it.dvdStore.service.DvdService; 
import com.ideas2it.dvdStore.service.impl.CategoryServiceImpl; 

import com.ideas2it.dvdStore.logger.DvdLogger;

/**
 * <p>
 * DvdService class is contains the operations of the Dvd Store
 * such as Insert new dvd to dvd store, delete existing dvd detail
 * in dvd store, update existing dvd details in dvd store, 
 * searching the dvd details...
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public class DvdServiceImpl implements DvdService {
    
    private DvdDao dvdDao = new DvdDaoImpl();  
         private DvdLogger logger = new DvdLogger();  

    /** 
     * @(inheritdoc)
     */
     public Boolean addDvd( Dvd dvd ) throws DvdException {
           return dvdDao.insertDvd(dvd);
     }

    /** 
     * @(inheritdoc)
     */
    public Set<Dvd> getDvds(Boolean status) throws DvdException {
        return dvdDao.getDvds(status);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteDvd(Dvd dvd) throws DvdException {
        return dvdDao.deleteDvd(dvd);
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Dvd> getDvdsByName(String name) throws DvdException {
        return dvdDao.getDvdsByName(name);
    }

    /** 
     * @(inheritdoc)
     */
    public Dvd getDvdById(Integer id, Boolean status) throws DvdException  {
        return dvdDao.getDvdById(id, status);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean updateDvd(Dvd dvd) throws DvdException {
        return dvdDao.updateDvd(dvd);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean restoreDvd(Dvd dvd) throws DvdException {
        return dvdDao.restoreDvd(dvd);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean reinsertDvd(Dvd dvd) throws DvdException {
        return dvdDao.reinsertDvd(dvd);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean isDvdExists(Dvd dvd, Boolean status) throws DvdException {
        return dvdDao.isDvdExists(dvd,status);
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Category> getCategories(Boolean status) throws DvdException {
        CategoryService categoryService = new CategoryServiceImpl();
        return categoryService.getCategories(status);
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Dvd> getDvdsByIds(Set<Dvd> dvds, Boolean status) throws DvdException  {
        return dvdDao.getDvdsByIds(dvds, status);
    }


}

