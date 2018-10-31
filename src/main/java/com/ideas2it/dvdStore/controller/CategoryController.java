package com.ideas2it.dvdStore.controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.service.CategoryService;
import com.ideas2it.dvdStore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdStore.utils.DvdUtils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;

/**
 *<p>
 * public CategoryController class to perform operations such as
 * add new dvd category to store, search dvd category, 
 * delete existing dvd category, update existing dvd category 
 *
 * This CategoryController class implements the functions 
 *  in the CategoryService class
 *
 * @author Anantharaj
 *</p>
 */  
@Controller
public class CategoryController {

    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * <p>
     * This method is used to show the admin menu.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "AdminOperations" is the view name
     * </p>
     */
    @RequestMapping(value="CategorytoAdmin", method = RequestMethod.POST)  
    public ModelAndView showAdminMenu() {  
        return new ModelAndView("AdminOperations");  
    }


    /**
     * <p>
     * This method is used to show the category menu.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryController" is the view name
     * </p>
     */
    @RequestMapping(value="CategoryMenu", method = RequestMethod.POST)  
    public ModelAndView showCategoryMenu() {
        return new ModelAndView("CategoryController");  
    }

    /**
     * <p>
     * This method is used to show the new category creation form.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryCreation" is view name and category object
     *        is the model object.
     * </p>
     */
    @RequestMapping(value="CreateCategory", method = RequestMethod.POST)  
    public ModelAndView CreateCategory() {  
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("button","save");
        modelAndView.setViewName("CategoryCreation");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to insert new category to the dvd store
     *       
     * @param category
     *       needed for insetring category object to dvd store
     * 
     * @param result
     *        BindingResult object will hold the validation errors.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryCreation" is the view name and category 
     *        is the model.
     * </p>
     */
    @RequestMapping(value="saveCategory", method = RequestMethod.POST)
    public ModelAndView saveCategory(@ModelAttribute("category") 
            Category category, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        try { 
            if (categoryService.addCategory(category)) {
                modelAndView.addObject( DvdConstants.STATUS, 
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                     category.getName() + DvdConstants.MSG_ADD_CATEGORY_SUCCESS);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    category.getName() + DvdConstants.MSG_ADD_CATEGORY_FAIL);  
            }
            modelAndView.addObject("category", new Category());
        } catch(DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("button","save");
        modelAndView.setViewName("CategoryCreation");
        return modelAndView; 
    }

    /**
     * <p>
     * This method is used to display all available categories in the dvdstore
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryDisplay" is the view name and  categories
     *        is the model.
     * </p>
     */
    @RequestMapping(value="displayCategory",method = RequestMethod.POST)
    public ModelAndView displayCategory() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Category> categories = categoryService.getCategories
                (Boolean.TRUE);
            if (0 >= categories.size()) {
                modelAndView.addObject(DvdConstants.LABEL_ISEMPTY,
                    DvdConstants.LABEL_EMPTY);
                modelAndView.addObject(DvdConstants.LABEL_EMPTYMESSAGE,
                    DvdConstants.MSG_DVD_EMPTY);
            }
            modelAndView.addObject("categories",categories);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("display","displayCategory");
        modelAndView.setViewName("CategoryDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to delete category from dvdstore based on user choice
     *        After deleting category, return to CategoryDisplay page.
     *
     * @param id
     *        needed for which category want delete.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryDisplay" is the view name and  categories
     *        is the model.
     * </p>
     */
    @RequestMapping(value="deleteCategory",method = RequestMethod.POST)
    public ModelAndView deleteCategory(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Category category = categoryService.getCategory
                (Integer.parseInt(id), Boolean.TRUE);
            if (categoryService.deleteCategory(category)) {
                modelAndView.addObject( DvdConstants.STATUS,
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                   DvdConstants.MSG_DELETE_DVD_SUCCESS + id);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    DvdConstants.MSG_DELETE_CATEGORY_SUCCESS + id);  
            }
            Set<Category> categories = categoryService.getCategories
                (Boolean.TRUE);
            modelAndView.addObject("categories",categories);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("display","displayCategory");
        modelAndView.setViewName("CategoryDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display all inactive categories in dvdstore
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryDisplay" is the view name and categories
     *        is the model.
     * </p>
     */
    @RequestMapping(value="displayInactiveCategory",method = RequestMethod.POST)
    public ModelAndView displayInactiveCategory() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Category> categories = categoryService.getCategories
                (Boolean.FALSE);
            if (0 >= categories.size()) {
                modelAndView.addObject(DvdConstants.LABEL_ISEMPTY,
                    DvdConstants.LABEL_EMPTY);
                modelAndView.addObject(DvdConstants.LABEL_EMPTYMESSAGE,
                    DvdConstants.MSG_CATEGORY_EMPTY);
            }
            modelAndView.addObject("categories",categories);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("display","restoreCategory");
        modelAndView.setViewName("CategoryDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to restore category from store based on user choice
     *        After restoring category, return to display inactivecategory page.
     *
     * @param id
     *        needed for which category want to restore from dvdstore..
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryDisplay" is the view name and categories
     *        is the model.
     * </p>
     */
    @RequestMapping(value="restoreCategory",method = RequestMethod.POST)
    public ModelAndView restoreCategory(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Category category = categoryService.getCategory
                (Integer.parseInt(id), Boolean.FALSE);
            if (categoryService.restoreCategory(category)) {
                modelAndView.addObject( DvdConstants.STATUS,
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE,
                    DvdConstants.MSG_RESTORE_CATEGORY_SUCCESS + id);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.STATUS,
                    DvdConstants.MSG_RESTORE_CATEGORY_FAIL);
            }
            Set<Category> categories = categoryService.getCategories
                (Boolean.FALSE);
            modelAndView.addObject("categories",categories);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("display","restoreCategory");
        modelAndView.setViewName("CategoryDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to show the category modification form with already 
     *        existing category values.
     *
     * @param id
     *        needed for get which category want modify from dvdstore.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryCreation" is the view name and category
     *        is the model.
     * </p>
     */
    @RequestMapping(value="modifyCategory", method = RequestMethod.POST)  
    public ModelAndView modifyCategory(@RequestParam("id") String id) {  
        ModelAndView modelAndView = new ModelAndView();
        try {
            Category category = categoryService.getCategory
                (Integer.parseInt(id), Boolean.TRUE);
            modelAndView.addObject("category",category);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("button","update");
        modelAndView.setViewName("CategoryCreation");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to update categroy in dvdstore based on user choice..
     *        After updating category, return to category display page.
     *
     * @param category
     *       needed for updating category object to dvd store
     * 
     * @param result
     *        BindingResult object will hold the validation errors.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CategoryDisplay" is the view name and categories
     *        is the model.
     * </p>
     */
    @RequestMapping(value="updateCategory",method = RequestMethod.POST)
    public ModelAndView updateCategory(@ModelAttribute("category") 
            Category category, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        try { 
            if (categoryService.updateCategory(category)) {
                modelAndView.addObject(DvdConstants.STATUS, 
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    category.getId()+DvdConstants.MSG_UPDATE_CATEGORY_SUCCESS);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    category.getId() + DvdConstants.MSG_UPDATE_CATEGORY_FAIL);  
            }
            Set<Category> categories = categoryService.getCategories
                (Boolean.TRUE);
            modelAndView.addObject("categories",categories);
        } catch(DvdException e){
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("display","displayCategory");
        modelAndView.setViewName("CategoryDisplay");
        return modelAndView;
    }


    /**
     * <p>
     * This method is used to search dvd from dvdstore based on category..
     *        If dvd exists in that name dvd will show in dvddisplay page.
     *
     * @param id
     *        needed for which dvds want to show from dvdstore
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of  dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="searchDvdByCategory", method = RequestMethod.POST)  
    public ModelAndView searchDvdByCategory(@RequestParam("id") String id) {  
        ModelAndView modelAndView = new ModelAndView();
        try {
            Category category = categoryService.getDvdsByCategory
                (Integer.parseInt(id));
            if (!(category.getDvds()).isEmpty()) {
                Set<Dvd> dvds = category.getDvds();
                modelAndView.addObject(DvdConstants.LABEL_DVDS,dvds);
            } else {

                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE,
                    DvdConstants.MSG_SERACH_BY_CATEGORY_FAIL);
            }

        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject(DvdConstants.DISPLAY,
            DvdConstants.LABEL_DISPLAYDVD);
        modelAndView.addObject("back","DvdFromcategory");
        modelAndView.setViewName("DvdDisplay");
        return modelAndView;
    }

}
