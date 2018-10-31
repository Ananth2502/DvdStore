package com.ideas2it.dvdStore.service; 

import java.util.Set;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.service.CategoryService;

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
public interface DvdService {
    
    /**
     *
     * <p>
     * This method is used to passing the dvd object to DAO layer 
     * for insert new  dvd to the store 
     *
     * @param dvd
     *       needed for passing dvd object to DAO layer
     *    
     * @return Boolean
     *       returns true if dvdObject added to list successfully
     *       Otherwise returns false
     * </p>
     *
     */
    public Boolean addDvd( Dvd dvd ) throws DvdException ; 

    /**
     * <p>
     * This method is used to get all active dvd details from dvd store 
     * 
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Dvd>
     *        returns the set of dvd details from dvd store
     * </p>
     *
     */
    public Set<Dvd> getDvds(Boolean status) throws DvdException ; 

    /**
     * <p>
     * It passes the dvd object to the dao to delete the dvd from dvd store
     *
     * @param dvd
     *      Needed for which dvd you want to delete
     *
     * @returns boolean
     *      If dvd detail cannot delete from store returns false. 
     *      it  returns true, if dvd delete from store
     * </p>
     */
    public Boolean deleteDvd(Dvd dvd) throws DvdException ; 

    /**
     * <p>
     * This method is used to get the particular dvd detail from store,
     *
     * @param id
     *        needed for which dvd you want to get from store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        returns dvd detail, if user provided dvd id is match with 
     *        any of the dvd id in the store,
     * </p>
     */
    public Dvd getDvdById(Integer id, Boolean status) throws DvdException ; 

    /**
     * <p>
     * Getting particular dvd detail from dvd store based on dvd ids
     *
     * @param dvds
     *        Needed for get the which dvd details from dvd store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Dvd>
     *        Returns set of dvd details form dvd store
     * </p>
     */
    public Set<Dvd> getDvdsByIds(Set<Dvd> dvds, Boolean status) 
        throws DvdException ; 

    /**
     * <p>
     * This method is used to get the dvd details from store, based on dvd name
     *
     * @param category
     *        needed for which dvd you want to get from store
     *
     * @return Set<Dvd>
     *        returns dvd details, which contains dvd names are  match with 
     *        user provided dvd name.
     * 
     *
     * </p>
     */
    public Set<Dvd> getDvdsByName(String name) throws DvdException ; 

    /**
     * <p>
     * This method is used to passing the updated dvd detail to the list
     *
     * @param dvd
     *        Needed for updating dvd
     * 
     * @return Boolean 
     *        Returns true if modify the existing dvd detail, otherwise false.
     * </p>
     */
    public Boolean updateDvd(Dvd dvd) throws DvdException;

    /**
     * <p>
     * This method is used to restoring the dvd detail
     *
     * @param dvd
     *        Needed for restoring dvd detail to list
     * 
     * @return Boolean 
     *        Returns true if restoring dvd detail, otherwise false.
     * </p>
     */
    public Boolean restoreDvd(Dvd dvd) throws DvdException;

    /**
     * <p>
     * This method is used to check, dvd is already exists in the dvd store,
     * based on name, release date, language in active status.
     *
     * @param dvd
     *        Needed for check dvd is already exists in store
     *
     * @param status
     *        Needed for dvd, which is active or inactive
     *
     * @return boolean
     *        Returns true, if any dvd name match with user provided dvd name.
     *        otherwise false.
     * </p>
     */
    public Boolean isDvdExists(Dvd dvd, Boolean status) throws DvdException; 

    /**
     * <p>
     * This method is used to get all available category details.
     * 
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Category>
     *        Returns the set of dvd details from dvd store
     * </p>
     */
    public Set<Category> getCategories(Boolean status) throws DvdException; 

    /**
     * <p>
     * This method is used to restoring the dvd detail
     *        
     * @param dvd
     *        Needed for restoring dvd detail to list
     * 
     * @return Boolean 
     *        Returns true if restoring dvd detail, otherwise false.
     * </p>
     */
    public Boolean reinsertDvd(Dvd dvd) throws DvdException;

}

