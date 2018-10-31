package com.ideas2it.dvdStore.dao;

import java.util.ArrayList;
import java.util.Set;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Dvd;

/**
 * <p>
 *
 * This interface class is used to insert the new dvd to dvd store, 
 * delete existing dvd in the dvd store, 
 * retrieve all available dvd details,
 * update existing dvd detail in the dvd store.
 *
 * @author Anantharaj
 *
 * </p>
 */
public interface DvdDao {

    /** 
     * <p>
     * Adding new dvd detail to dvd store
     * 
     * @param dvd
     *        Needed for inserting this dvd details to dvd store
     *
     * @return boolean
     *        If dvd details added to store retuns true, otherwise false
     * </p> 
     */
    public Boolean insertDvd(Dvd dvd)  throws DvdException ;

    /**
     * <p>
     * Getting all active dvd details from dvd store 
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Dvd>
     *        Returns set of dvd details form dvd store
     * </p>
     */
    public Set<Dvd> getDvds(Boolean status) throws DvdException ; 

    /**
     * <p>
     * Getting particular dvd detail from dvd store based on dvd id
     *
     * @param id
     *        Needed for get the dvd detail from dvd store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        Returns particular dvd details form dvd store
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
     * Getting dvd details from dvd store based on dvd name
     *
     * @param name
     *        Needed for get the dvd details from dvd store
     *
     * @return Set<Dvd>
     *        Returns dvd details form dvd store
     * </p>
     */
    public Set<Dvd> getDvdsByName(String name) throws DvdException ; 

    /** 
     * <p>
     * Delete existing dvd detail in the dvd store, if available
     * 
     * @param dvd
     *        Needed for deleting this dvd details in the dvd store
     *
     * @return Boolean
     *        If dvd details deleted in store retuns true, otherwise false
     * </p> 
     */
    public Boolean deleteDvd(Dvd dvd) throws DvdException ;

    /** 
     * <p>
     * Update existing dvd detail in the dvd store, if available.
     * 
     * @param dvd
     *        Needed for updating this dvd details in dvd store
     *
     * @return boolean
     *        If dvd details added to store retuns true, otherwise false
     * </p> 
     */
    public Boolean updateDvd(Dvd dvd) throws DvdException;

    /** 
     * <p>
     * Restoring dvd detail in the dvd store, if available.
     * 
     * @param dvd
     *        Needed for restoring dvd details 
     *
     * @return boolean
     *        If dvd details restored to store retuns true, otherwise false
     * </p> 
     */
    public Boolean restoreDvd(Dvd dvd) throws DvdException;

    /**
     * <p>
     * This method is used to check the dvd details is already exists or not 
     * in the dvd store based on name,languange,release date
     *
     * @param dvd
     *        Needed for check dvd is already exists in store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return boolean
     *        Returns true, if entered dvd details already exists
     * </p>
     */
    public Boolean isDvdExists(Dvd dvd, Boolean status) throws DvdException; 

    /**
     * <p>
     * This method is used to restore the dvd details is already exists in 
     * inactive status based on name,languange,release date at the time of
     * inserting new dvd with same datas of deleted dvd.
     *
     * @param dvd
     *        Needed for check dvd is already exists in store
     *
     * @return boolean
     *        Returns true, if restored dvd details, otherwise false
     * </p>
     */
    public Boolean reinsertDvd(Dvd dvd) throws DvdException; 

}
