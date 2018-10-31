package com.ideas2it.dvdStore.service.impl; 

import java.util.Set;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.CategoryDao;
import com.ideas2it.dvdStore.dao.impl.CategoryDaoImpl;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.service.CategoryService; 

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
public class CategoryServiceImpl implements CategoryService {
    
    private CategoryDao categoryDao = new CategoryDaoImpl();  

    /** 
     * @(inheritdoc)
     */
     public Boolean addCategory( Category category ) throws DvdException {
         return categoryDao.insertCategory(category);
     }


    /** 
     * @(inheritdoc)
     */
/*     public Integer addCategory( Category category ) throws DvdException {
         if (categoryDao.isCategoryExists(category, Boolean.TRUE)) {
             return 2;
         } 
         if (categoryDao.isCategoryExists(category, Boolean.FALSE)) {
             if (categoryDao.restoreCategory(category)) {
                return 1;
             }
             return 0;
         }
         if (categoryDao.insertCategory(category)) {
             return 1;
         }
         return 0;
     }  */

    /** 
     * @(inheritdoc)
     */
    public Set<Category> getCategories(Boolean status) throws DvdException {
        Set<Category> categories = categoryDao.getCategories(status);
        return categories;
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteCategory(Category category) throws DvdException {
        return categoryDao.deleteCategory(category);
    }

    /** 
     * @(inheritdoc)
     */
    public Category getCategory(Integer id, Boolean status) throws DvdException {
        return categoryDao.getCategory(id, status);
    }

    /** 
     * @(inheritdoc)
     */
    public Category getDvdsByCategory(Integer id) throws DvdException {
        return categoryDao.getDvdsByCategory(id);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean updateCategory(Category category) throws DvdException {
        return categoryDao.updateCategory(category);

/*         if (categoryDao.isCategoryExists(category, Boolean.TRUE)) {
             return 2;
         }
         if (categoryDao.isCategoryExists(category, Boolean.FALSE)) {
             return 3;
         }
         if (categoryDao.updateCategory(category)) {
             return 1;
         }
         return 0;
*/
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean restoreCategory(Category category) throws DvdException {
        return categoryDao.restoreCategory(category);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean isCategoryExists(Category category, Boolean status)
            throws DvdException {
        return categoryDao.isCategoryExists(category, status);
    }

}

