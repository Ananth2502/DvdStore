package com.ideas2it.dvdStore.service; 

import java.util.Set;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;

/**
 * <p>
 * categoryService class is contains the operations of the Dvd Store
 * such as Insert new category to dvd store, delete existing category
 * detail in dvd store, update existing category details in dvd store, 
 * getting the category details from dvd store...
 *
 * This class have the methods of the category functions
 * </p>
 */
public interface CategoryService {
    
    /**
     * <p>
     * This method is used to passing the dvd object to DAO layer 
     * for insert new  dvd to the store 
     *
     * @param dvd
     *       needed for passing dvd object to DAO layer
     *    
     * @return Boolean
     *       returns true, if category added to successfully, otherwise false
     * </p>
     */
    public Boolean addCategory(Category category) throws DvdException; 

    /**
     * <p>
     * This method is used to get category details from dvd store 
     * 
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Dvd>
     *        returns the Set of dvd details from dvd store
     * </p>
     */
    public Set<Category> getCategories(Boolean status) throws DvdException; 

    /**
     * <p>
     * It passes the dvd id to the dao to delete the dvd from dvd store
     *
     * @param category
     *      Needed for which category you want to delete
     *
     * @returns boolean
     *      If dvd detail cannot delete from store returns false. 
     *      it  returns true, if dvd delete from store
     * </p>
     */
    public Boolean deleteCategory(Category category) throws DvdException;

    /**
     * <p>
     * This method is used to get the particular dvd detail from store,
     *
     * @param id
     *        needed for which category you want to get from store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        returns dvd detail, if user provided dvd id is match with 
     *        any of the dvd id in the store,
     *        otherwise returns null
     * </p>
     */
    public Category getCategory(Integer id, Boolean status) throws DvdException; 

    /**
     * <p>
     * This method is used to get the dvd details from store, based on category
     *
     * @param category
     *        needed for which dvd you want to get from store
     *
     * @return Category
     *        returns dvd details, if dvd category is  match with the
     *        user provided dvd category.
     * </p>
     */
    public Category getDvdsByCategory(Integer id) throws DvdException;

    /**
     * <p>
     * This method is used to passing the updated dvd detail to the Set
     *        
     * @param dvd
     *        needed for adding dvd object to Set
     * 
     * @return boolean 
     *        returns true if modify the existing boolean detail, otherwise 
     *        returns false.
     * </p>
     */
    public Boolean updateCategory(Category category) throws DvdException; 

    /**
     * <p>
     * This method is used to restoring the category detail
     *        
     * @param category
     *        Needed for restoring category detail to Set
     * 
     * @return boolean 
     *        Returns true if restoring category detail, otherwise false.
     * </p>
     */
    public Boolean restoreCategory(Category category) throws DvdException;

    /**
     * <p>
     * This method is used to check, category is already exists in active status
     * based on category name.
     *
     * @param category
     *        needed for check category is already exists in store
     *     
     * @param status
     *        Needed for check category is active or inactive
     *
     * @return boolean
     *        returns true, if category match with user provided name.
     *        otherwise false.
     * </p>
     */
    public Boolean isCategoryExists(Category category, Boolean status)
        throws DvdException; 
 
}

