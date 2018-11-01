package com.ideas2it.dvdStore.controller;

import java.util.LinkedHashSet;
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
import com.ideas2it.dvdStore.model.Address;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.service.CustomerService;
import com.ideas2it.dvdStore.service.UserService;
import com.ideas2it.dvdStore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdStore.service.impl.UserServiceImpl;

/**
 *<p>
 * UserController class to perform operations such as
 * add new customer, login with customer and admin, logout user, etc..
 *
 * This UserController class implements the functions in the UserService class.
 *
 * @author Anantharaj
 *</p>
 */  
@Controller
public class UserController extends HttpServlet {


    /**
     * <p>
     * This method is used to show the login page.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     * this method "login" is the view name
     * </p>
     */
    @RequestMapping(value="DvdStore", method = RequestMethod.GET)  
    public ModelAndView showIndex() {  
        return new ModelAndView("Login");  
    }

    /**
     * <p>
     * This method is used to show the login page.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     * this method "login" is the view name
     * </p>
     */
    @RequestMapping(value="/LoginPage", method = RequestMethod.POST)  
    public ModelAndView showlogin() {  
        return new ModelAndView("Login");  
    }

    /**
     * <p>
     * This method is used to show the create new customer registration form.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     * this method "CustomerCreation" is the view name and customer is the
     * model object.
     * </p>
     */
    @RequestMapping(value="/CustomerRegister", method = RequestMethod.POST)  
    public ModelAndView CustomerCreation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer", new Customer());
        modelAndView.setViewName("CustomerCreation");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to show the add new customer to dvd store.
     *      
     * @param request
     *        A request message from a client to a server includes, within the 
     *        first line of that message, the method to be applied to 
     *        the resource, the identifier of the resource in use.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     * this method "login" is the view name. After creating customer return 
     * login page.
     * </p>
     */
    @RequestMapping(value="/AddCustomer", method = RequestMethod.POST)
    public ModelAndView addCustomer(@ModelAttribute("customer")
            Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        UserService userService = new UserServiceImpl();
        try {
            if (userService.addCustomer(customer)) {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.SUCCESS);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    customer.getName() + DvdConstants.MSG_ADD_CUSTOMER_SUCCESS);
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE, 
                    customer.getName() + DvdConstants.MSG_ADD_CUSTOMER_FAIL);

            }
        } catch (DvdException e) {
            modelAndView.addObject("message",e.getMessage());
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
        }
        modelAndView.setViewName("Login");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to select the user is customer or admin and proceed 
     * login as a customer or admin based on user choice..
     *     
     * @param request
     *        A request message from a client to a server includes, within the 
     *        first line of that message, the method to be applied to 
     *        the resource, the identifier of the resource in use.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. 
     * This return object is based on user selection, if user is customer
     * customerOperations is view name. if user is admin, adminOperations is the
     * view name
     * </p>
     */
    @RequestMapping(value="/SignIn", method = RequestMethod.POST)
    public ModelAndView signIn(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String role = request.getParameter("user");
        if (role.equals("admin")) {
             return loginAdmin(request);
        } else {
            return loginCustomer(request);
        }
    }

    /**
     * <p>
     * This method is used to login as a customer in the dvdstore
     *     
     * @param request
     *        A request message from a client to a server includes, within the 
     *        first line of that message, the method to be applied to 
     *        the resource, the identifier of the resource in use.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In
     * this method customerOperations is view name, if customer login 
     * credentials accept to dvdStore. 
     * </p>
     */
    public ModelAndView loginCustomer(HttpServletRequest request) {
        UserService loginService = new UserServiceImpl();
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = new User();
            user.setUserId(request.getParameter("userId"));
            user.setPassword(request.getParameter(DvdConstants.LABEL_PASSWORD));
            user.setRole("CUSTOMER");
            User customer = loginService.userLogin(user);


            if (null != customer) {
                HttpSession session = request.getSession();
                session.setAttribute("userid", customer.getCustomerId());
                session.setAttribute("name", customer.getUserId());
                modelAndView.setViewName("CustomerOperations");
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE,
                    "User Id or Password not a valid credentials");
                modelAndView.setViewName("Login");
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE,e.getMessage());
            modelAndView.setViewName("Login");
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to admin login into dvdstore
     *
     * @param request
     *        A request message from a client to a server includes, within the 
     *        first line of that message, the method to be applied to 
     *        the resource, the identifier of the resource in use.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In
     * this method addminOperations is view name, if admin login credentials
     * accept to dvdStore. 
     * </p>
     */
    public ModelAndView loginAdmin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = new User();
            user.setUserId(request.getParameter("userId"));
            user.setPassword(request.getParameter(DvdConstants.LABEL_PASSWORD));
            user.setRole("ADMIN");
            UserService userService = new UserServiceImpl();
            User admin = userService.userLogin(user);
            if (null != admin) {
                HttpSession session = request.getSession();
                session.setAttribute("userid", admin.getCustomerId());
                session.setAttribute("name", admin.getUserId());
                modelAndView.setViewName("AdminOperations");
            } else {
                modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
                modelAndView.addObject(DvdConstants.MESSAGE,
                    "User Id or Password not a valid credentials");
                modelAndView.setViewName("Login");
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
            modelAndView.setViewName("Login");
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to sign out the customer and admin
     *
     * @param request
     *        A request message from a client to a server includes, within the 
     *        first line of that message, the method to be applied to 
     *        the resource, the identifier of the resource in use.
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     * this method "login" is the view name
     * </p>
     */
    @RequestMapping(value="/signOut", method = RequestMethod.POST)
    public ModelAndView signOut(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session=request.getSession(false);
        session.removeAttribute("userid");
        session.invalidate();  
        modelAndView.addObject(DvdConstants.STATUS,DvdConstants.SUCCESS);
        modelAndView.addObject(DvdConstants.MESSAGE,"Logout successfully!!!");
        modelAndView.setViewName("Login");
        return modelAndView;
    }

}
