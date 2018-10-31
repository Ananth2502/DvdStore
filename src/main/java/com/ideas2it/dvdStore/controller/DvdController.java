package com.ideas2it.dvdStore.controller;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle; 
import java.time.LocalDate;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashSet;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

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

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.service.CategoryService;
import com.ideas2it.dvdStore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdStore.service.DvdService;
import com.ideas2it.dvdStore.service.impl.DvdServiceImpl;
import com.ideas2it.dvdStore.utils.DateUtils;
import com.ideas2it.dvdStore.utils.DvdUtils;

/**
 *<p>
 * public DvdController class to perform operations such as
 * insert new dvd to store, search dvd detials in store , 
 * delete existing dvd in the store, update existing dvd in dvd store,
 * sear
 *
 * This DvdStoreController class implements the functions 
 *        in the DvdService interfaces
 *</p>
 */  
@Controller
public class DvdController extends HttpServlet {

    private Scanner reader = new Scanner(System.in);
    private DvdService dvdService = new DvdServiceImpl();
    private Set<Category> categories = new LinkedHashSet<Category>();

    /**
     * <p>
     * This method is used to show the admin menu.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "AdminOperations" is the view name
     * </p>
     */
    @RequestMapping(value="DvdtoAdmin", method = RequestMethod.POST)  
    public ModelAndView showAdminMenu() {  
        return new ModelAndView("AdminOperations");  
    }

    /**
     * <p>
     * This method is used to show the dvd menu.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdController" is the view name
     * </p>
     */
    @RequestMapping(value="DvdMenu", method = RequestMethod.POST)  
    public ModelAndView showDvdMenu() {  
        return new ModelAndView("DvdController");  
    }

    /**
     * <p>
     * This method is used to show the new dvd creation form.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdCreation" is the view name and categories and dvd
     *        is the model object.
     * </p>
     */
    @RequestMapping(value="CreateDvd", method = RequestMethod.POST)  
    public ModelAndView CreateDvd() {  
        ModelAndView modelAndView = new ModelAndView();
        Set<Category> categories = new LinkedHashSet<Category>();
        try {
            CategoryService categoryService = new CategoryServiceImpl();
            categories = dvdService.getCategories(Boolean.TRUE);
            modelAndView.addObject("dvd", new Dvd());
            modelAndView.addObject("categories",categories);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("button","save");
        modelAndView.setViewName("DvdCreation");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to insert new dvd to the dvd store
     *       
     * @param request
     *        A request message from a client to a server includes, within the 
     *        first line of that message, the method to be applied to 
     *        the resource, the identifier of the resource in use.
     *
     * @param dvd
     *       needed for insetring dvd object to dvd store
     * 
     * @param result
     *        BindingResult object will hold the validation errors.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdCreation" is the view name and categories and dvd
     *        is the models.
     * </p>
     */
    @RequestMapping(value="saveDvd", method = RequestMethod.POST)
    public ModelAndView addDvd(@ModelAttribute("dvd") Dvd dvd,
            BindingResult result,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] values = request.getParameterValues("categories");
        Set<Category> categoryCollection = new LinkedHashSet<Category>();
        for (Integer i=0;i<values.length;i++) { 
              Category category = new Category();
              category.setId(Integer.parseInt(values[i]));
              categoryCollection.add(category);
        }
        dvd.setCategories(categoryCollection);
        try { 
            if (dvdService.addDvd(dvd)) {
                modelAndView.addObject(DvdConstants.STATUS,
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    dvd.getName() + DvdConstants.MSG_ADD_DVD_SUCCESS);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    dvd.getName() + (DvdConstants.MSG_ADD_DVD_FAIL));  
            }
            modelAndView.addObject("dvd", new Dvd());
            CategoryService categoryService = new CategoryServiceImpl();
            Set<Category> categories = dvdService.getCategories
                (Boolean.TRUE);
            modelAndView.addObject("categories",categories);
        } catch(DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("button","save");
        modelAndView.setViewName("DvdCreation");
        return modelAndView; 
    }

    /**
     * <p>
     * This method is used to display all available dvds in the dvdstore
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="displayDvd",method = RequestMethod.POST)
    public ModelAndView displayDvd() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Dvd> dvds = dvdService.getDvds(Boolean.TRUE);
            if (0 >= dvds.size()) {
                modelAndView.addObject(DvdConstants.LABEL_ISEMPTY,
                    DvdConstants.LABEL_EMPTY);
                modelAndView.addObject(DvdConstants.LABEL_EMPTYMESSAGE,
                    DvdConstants.MSG_DVD_EMPTY);
            }
            modelAndView.addObject("dvds",dvds);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject(DvdConstants.DISPLAY,
            DvdConstants.LABEL_DISPLAYDVD);
        modelAndView.setViewName("DvdDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to show the dvd modification form with already 
     *        existing dvd values.
     *
     * @param id
     *        needed for get which dvd want modify from dvdstore.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdCreation" is the view name and categories and dvd
     *        is the models.
     * </p>
     */
    @RequestMapping(value="modifyDvd",method = RequestMethod.POST)
    public ModelAndView modifyDvd(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Dvd dvd = dvdService.getDvdById(Integer.parseInt(id), Boolean.TRUE);
            categories = dvdService.getCategories(Boolean.TRUE);
            modelAndView.addObject("categories",categories);
            modelAndView.addObject("dvd", dvd);

        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("button","update");
        modelAndView.setViewName("DvdCreation");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to delete dvd from dvdstore based on user choice..
     *        After deleting dvd from dvdstore return to dvd display page.
     *
     * @param id
     *        needed for which dvd want delete.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of  dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="deleteDvd",method = RequestMethod.POST)
    public ModelAndView deleteDvd(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Dvd dvd = dvdService.getDvdById(Integer.parseInt(id), Boolean.TRUE);
            if (dvdService.deleteDvd(dvd)) {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    dvd.getName() + DvdConstants.MSG_DELETE_DVD_SUCCESS);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    dvd.getName() + DvdConstants.MSG_DELETE_DVD_FAIL);  
            }
            Set<Dvd> dvds = dvdService.getDvds(Boolean.TRUE);
            modelAndView.addObject("dvds",dvds);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject(DvdConstants.DISPLAY,
             DvdConstants.LABEL_DISPLAYDVD);
        modelAndView.setViewName("DvdDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to upadte dvd in the dvdstore based on user choice..
     *        After updating dvd in dvdstore return to dvd display page.
     *
     * @param dvd
     *       needed for updating dvd object to dvd store
     * 
     * @param result
     *        BindingResult object will hold the validation errors.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="updateDvd", method = RequestMethod.POST)
    public ModelAndView updateDvd(@ModelAttribute("dvd") Dvd dvd,
            BindingResult result,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] values = request.getParameterValues("categories");
        Set<Category> categoryCollection = new LinkedHashSet<Category>();
        for (Integer i=0;i<values.length;i++) { 
              Category category = new Category();
              category.setId(Integer.parseInt(values[i]));
              categoryCollection.add(category);
        }
        dvd.setCategories(categoryCollection);
        try { 
            if (dvdService.updateDvd(dvd)) {
                modelAndView.addObject(DvdConstants.STATUS,
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    dvd.getName() + DvdConstants.MSG_UPDATE_DVD_SUCCESS);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    dvd.getName() + DvdConstants.MSG_UPDATE_DVD_FAIL);
            }
            Set<Dvd> dvds = dvdService.getDvds(Boolean.TRUE);
            modelAndView.addObject("dvds",dvds);

        } catch(DvdException e){
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject(DvdConstants.DISPLAY,
            DvdConstants.LABEL_DISPLAYDVD);
        modelAndView.setViewName("DvdDisplay");
        return modelAndView; 
    }

    /**
     * <p>
     * This method is used to display all available inactive dvds in dvdstore
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="displayInactiveDvd",method = RequestMethod.POST)
    public ModelAndView displayInactiveDvd() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Dvd> dvds = dvdService.getDvds(Boolean.FALSE);
            if (0 >= dvds.size()) {
                modelAndView.addObject(DvdConstants.DISPLAY,
                    DvdConstants.LABEL_RESTOREDVD);
                modelAndView.addObject(DvdConstants.LABEL_ISEMPTY,
                    DvdConstants.LABEL_EMPTY);
                modelAndView.addObject(DvdConstants.LABEL_EMPTYMESSAGE, 
                    DvdConstants.MSG_EMPTY_DVD_TRASH);
            }
            modelAndView.addObject("dvds",dvds);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject(DvdConstants.DISPLAY,
            DvdConstants.LABEL_RESTOREDVD);
        modelAndView.setViewName("DvdDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to restore dvd from dvdstore based on user choice..
     *        After restoring dvd, return to display inactive dvd page.
     *
     * @param id
     *        needed for which dvd want to restore from dvdstore..
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of  dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="restoreDvd",method = RequestMethod.POST)
    public ModelAndView restoreDvd(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Dvd dvd = dvdService.getDvdById(Integer.parseInt(id),Boolean.FALSE);
            if (dvdService.restoreDvd(dvd)) {
                modelAndView.addObject(DvdConstants.STATUS,
                    DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE,
                    DvdConstants.MSG_RESTORE_DVD_SUCCESS + id);
                Set<Dvd> dvds = dvdService.getDvds(Boolean.FALSE);
                modelAndView.addObject("dvds",dvds);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE,
                    DvdConstants.MSG_RESTORE_DVD_FAIL + id);
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject(DvdConstants.DISPLAY,
            DvdConstants.LABEL_RESTOREDVD);
        modelAndView.setViewName("DvdDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to show the dvd serach page.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdSearch" is the view name.
     * </p>
     */
    @RequestMapping(value="searchDvd", method = RequestMethod.POST)  
    public ModelAndView searchDvd() {  
        return new ModelAndView("DvdSearch");  
    }

    /**
     * <p>
     * This method is used to search dvd from dvdstore based on dvd name..
     *        If dvd exists in that name dvd will show in dvddisplay page.
     *
     * @param name
     *        needed for which dvds want to show from dvdstore
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of  dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="searchDvdByName",method = RequestMethod.POST)
    public ModelAndView searchDvdByName(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Dvd> dvds = dvdService.getDvdsByName(name);
            if (0 <  dvds.size()) {
                modelAndView.addObject(DvdConstants.DISPLAY,
                    DvdConstants.LABEL_DISPLAYDVD);
                modelAndView.addObject(DvdConstants.LABEL_DVDS,dvds);
                modelAndView.setViewName("DvdDisplay");
            } else {
                modelAndView.addObject(DvdConstants.NAME,name);
                modelAndView.addObject(DvdConstants.LABEL_ISEMPTY,
                    DvdConstants.LABEL_EMPTY);
                modelAndView.addObject(DvdConstants.LABEL_EMPTYMESSAGE,
                    name + DvdConstants.MSG_SEARCH_DVD_FAIL);
                modelAndView.setViewName("DvdController");
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to search dvd from dvdstore based on dvd id..
     *        If dvd exists in that id, dvd will show in dvddisplay page.
     *
     * @param id
     *        needed for which dvd want to show from dvdstore
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "DvdDisplay" is the view name and set of  dvds
     *        is the model.
     * </p>
     */
    @RequestMapping(value="searchDvdById",method = RequestMethod.POST)
    public ModelAndView searchDvdById(@RequestParam("dvdid") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Dvd dvd = dvdService.getDvdById(id, Boolean.TRUE);
            Set<Dvd> dvds = new LinkedHashSet<Dvd>();
            dvds.add(dvd);
            if (null != dvd) {
                modelAndView.addObject(DvdConstants.LABEL_DVDS,dvds);
                modelAndView.addObject(DvdConstants.DISPLAY,
                    DvdConstants.LABEL_DISPLAYDVD);
                modelAndView.setViewName("DvdDisplay");
            } else {
                modelAndView.addObject(DvdConstants.ID,id);
                modelAndView.addObject(DvdConstants.LABEL_ISEMPTY,
                    DvdConstants.LABEL_EMPTY);
                modelAndView.addObject(DvdConstants.LABEL_EMPTYMESSAGE,
                    id + DvdConstants.MSG_SEARCH_DVD_FAIL);
                modelAndView.setViewName("DvdController");
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
}
