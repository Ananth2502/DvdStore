package com.ideas2it.dvdStore.dao;

import java.util.Set;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;

/**
 * <p>
 *
 * This interface class is used to insert the new category to dvd store, 
 * delete existing category in the dvd store, retrieve all available category 
 * details, update existing category detail in the dvd store.
 *
 * @author Anantharaj
 *
 * </p>
 */
public interface CategoryDao {

    /** 
     * <p>
     * Adding category dvd detail to dvd store
     * 
     * @param category
     *        Needed for inserting category details to dvd store
     *
     * @return boolean
     *         if category details added to store retuns true, otherwise false
     * </p> 
     */
    public Boolean insertCategory(Category category) throws DvdException;

    /**
     * <p>
     * Getting all active category details from dvd store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Category>
     *       returns set of category details form dvd store
     * </p>
     */
    public Set<Category> getCategories(Boolean status) throws DvdException; 

    /** 
     * <p>
     * Delete existing category detail in the dvd store, if available
     * 
     * @param category
     *       Needed for deleting this category details in the dvd store
     *
     * @return boolean
     *       if category details deleted in store retuns true, otherwise false
     * </p> 
     */
    public Boolean deleteCategory(Category category) throws DvdException;

    /**
     * <p>
     * Getting particular category detail from dvd store based on category id
     *
     * @param id
     *       Needed for get which category detail  in the dvd store
     *
     * @return category
     *       returns particular category details form dvd store
     * </p>
     */
    public Category getCategory(Integer id, Boolean status) throws DvdException; 

    /**
     * <p>
     * Getting dvd details from dvd store based on dvd category 
     *
     * @param id
     *        Needed for get the dvd details from dvd store
     *
     * @return Set<Dvd>
     *        Returns dvd details form dvd store
     * </p>
     */
    public Category getDvdsByCategory(Integer id) throws DvdException ;

    /** 
     * <p>
     * Update existing category detail in the dvd store, if available.
     * 
     * @param category
     *       Needed for updating this category details in dvd store
     *
     * @return boolean
     *       if category details updated to store retuns true, otherwise false
     * </p> 
     */
    public Boolean updateCategory(Category category) throws DvdException; 

    /** 
     * <p>
     * Restoring category detail in the dvd store, if available.
     * 
     * @param category
     *        Needed for restoring category details 
     *
     * @return boolean
     *        If category restored to store retuns true, otherwise false
     * </p> 
     */
    public Boolean restoreCategory(Category category) throws DvdException;

    /**
     * <p>
     * This method is used to check the category in active status, before 
     * inserting and updating category detail store for category is already  
     * exists in the dvd store or not
     *
     * @param category
     *       Needed for check category detail  in the dvd store
     *     
     * @param status
     *        Needed for check category is active or inactive
     *
     * @return boolean
     *       returns true if category already exists in the dvdstore,
     *       otherwise false
     * </p>
     */
    public Boolean isCategoryExists(Category category, Boolean status)
        throws DvdException; 

}
