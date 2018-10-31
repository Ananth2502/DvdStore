package com.ideas2it.dvdStore.controller;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

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
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.Orders;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.service.CustomerService;
import com.ideas2it.dvdStore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdStore.utils.DvdUtils;

/**
 *<p>
 * CustomerController class to perform operations such as
 * add new customer, delete existing customer, update customer details. 
 *
 * This CustomerController class implements the functions 
 *  in the CustomerService class
 *
 * @author Anantharaj
 *</p>
 */  
@Controller
public class CustomerController extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * <p>
     * This method is used to show the customer menu.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CustomerOperations" is the view name
     * </p>
     */
    @RequestMapping(value="backtoCustomer", method = RequestMethod.POST)  
    public ModelAndView backtoCustomer() {  
        return new ModelAndView("CustomerOperations");  
    }

    /**
     * <p>
     * This method is used to show the admin menu.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "AdminOperations" is the view name
     * </p>
     */
    @RequestMapping(value="CustomertoAdmin", method = RequestMethod.POST)  
    public ModelAndView showAdminMenu() {  
        return new ModelAndView("AdminOperations");  
    }

    /**
     * <p>
     * This method is used to show the search customer form.
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "CustomerSearch" is the view name
     * </p>
     */
    @RequestMapping(value="SearchCustomer", method = RequestMethod.POST)  
    public ModelAndView SearchCustomer() {  
        return new ModelAndView("CustomerSearch");  
    }

    /**
     * <p>
     * This method is used to search customer from dvdstore based on customerid.
     *        If customer exists in that id, show in customer page.
     *
     * @param id
     *        needed for which cuatomer want to search from dvdstore
     * 
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "ViewAddress" is the view name and customer
     *        is the model. if customer not exists customerserach is the view.
     * </p>
     */
    @RequestMapping(value="searchCustomerById",method = RequestMethod.POST)
    public ModelAndView searchCustomerById(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Customer customer = customerService.getCustomerById
                (Integer.parseInt(id), Boolean.TRUE);
            if (null != customer) {
                modelAndView.addObject("customer",customer);
                modelAndView.setViewName("ViewAddress");
            } else {
                modelAndView.addObject("isEmpty","empty");
                modelAndView.addObject("emptyMessage",
                    DvdConstants.MSG_SERACH_CUSTOMER_BY_ID_FAIL);
                modelAndView.setViewName("CustomerSearch");
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value="searchCustomerByMobile",method = RequestMethod.POST)
    public ModelAndView searchCustomerByMobile(@RequestParam("mobile") 
            String mobile) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Customer customer = customerService.getCustomerByMobile
                (mobile, Boolean.TRUE);
            if (null != customer) {
                modelAndView.addObject("customer",customer);
                modelAndView.setViewName("ViewAddress");
            } else {
                modelAndView.addObject("isEmpty","empty");
                modelAndView.addObject("emptyMessage",
                    DvdConstants.MSG_SERACH_CUSTOMER_BY_MOBILE_FAIL);
                modelAndView.setViewName("CustomerSearch");
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value="displayAllCustomers",method = RequestMethod.POST)
    public ModelAndView displayAllCustomers() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Customer> customers = customerService.getCustomers
                (Boolean.TRUE);
            if (0 >= customers.size()) {
                modelAndView.addObject("isEmpty","empty");
                modelAndView.addObject("emptyMessage",
                    DvdConstants.MSG_CUSTOMER_EMPTY);
            }
            modelAndView.addObject("customers",customers);
            modelAndView.setViewName("CustomerDisplay");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value="CustomerDetail",method = RequestMethod.POST)
    public ModelAndView displayCustomer(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Customer customer = customerService.getCustomerById
                (Integer.parseInt(id), Boolean.TRUE);
            modelAndView.addObject("customer",customer);
            modelAndView.setViewName("ViewAddress");
        } catch (DvdException e) {}
        return modelAndView;
    }

    @RequestMapping(value="displayAllOrders",method = RequestMethod.POST)
    public ModelAndView displayAllOrders() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Set<Orders> orders = customerService.getOrders(0);
            if (0 >= orders.size()) {
                modelAndView.addObject("isEmpty","empty");
                modelAndView.addObject("emptyMessage", DvdConstants.MSG_NO_ORDER_PLACED);
            }
            modelAndView.addObject("user","admin");
            modelAndView.addObject("orders",orders);
            modelAndView.setViewName("OrdersDisplay");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value="myOrders",method = RequestMethod.POST)
    public ModelAndView viewOrdersList( HttpServletRequest request) {
        HttpSession session=request.getSession(false);  
        ModelAndView modelAndView = new ModelAndView();
        Integer id = (Integer)session.getAttribute("userid");  
        try {
            Set<Orders> orders = customerService.getOrders(id);
            if (null == orders) {
                modelAndView.addObject("isEmpty","empty");
                modelAndView.addObject("emptyMessage", 
                    DvdConstants.MSG_NO_ORDER_PLACED);
            }
            modelAndView.addObject("orders",orders);
            modelAndView.addObject("user","customer");
            modelAndView.setViewName("OrdersDisplay");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value="cancelOrder",method = RequestMethod.POST)
    public ModelAndView cancelOrder(HttpServletRequest request,
            @RequestParam("id") String id) {
        HttpSession session=request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        try {
            Orders orders = new Orders();
            orders.setId(Integer.parseInt(id));
            if (customerService.deleteOrder(orders)) {
                modelAndView.addObject("status","success");
                modelAndView.addObject("message",
                    DvdConstants.MSG_CANCEL_ORDER_SUCCESS + id);
            } else {
                modelAndView.addObject("status","fail");
                modelAndView.addObject("message",
                    DvdConstants.MSG_CANCEL_ORDER_FAIL + id);
            }
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return viewOrdersList(request);
    }

   @RequestMapping(value="purchaseDvd",method = RequestMethod.POST)
   public ModelAndView purchaseDvd(HttpServletRequest request) {
        HttpSession session=request.getSession(false);  
        ModelAndView modelAndView = new ModelAndView();
        Integer id = (Integer)session.getAttribute("userid");  
        try {
            Set<Dvd> dvds = customerService.getDvds(Boolean.TRUE);
            List<Address> addresses = customerService.getAddress(id);
            if (0 < dvds.size()) {
                modelAndView.addObject("addresses",addresses);
                modelAndView.addObject("dvds",dvds);
            } else {
                modelAndView.addObject("isEmpty","empty");
                modelAndView.addObject("emptyMessage", 
                    "No dvds available in store..");
            }
            modelAndView.addObject("display","purchaseDvd");
            modelAndView.setViewName("DvdDisplay");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

   @RequestMapping(value="placeOrder",method = RequestMethod.POST)
    public ModelAndView placeOrder(HttpServletRequest request) {
        HttpSession session=request.getSession(false);  
        ModelAndView modelAndView = new ModelAndView();
        Integer id = (Integer)session.getAttribute("userid");  
        try {
            String[] dvds = request.getParameterValues("check");
            Set<Dvd> dvdCollection = new LinkedHashSet<Dvd>();
            for (Integer i=0; i < dvds.length; i++) { 
                Dvd dvd = new Dvd();
                dvd.setId(Integer.parseInt(dvds[i]));
                dvdCollection.add(dvd);
            }
            Integer addressId = Integer.parseInt(request.getParameter
                ("addressId"));
            Orders orders = new Orders();
            Customer customer = new Customer(); 
            customer.setId(id);
            orders.setCustomer(customer);
            Address address = new Address(); 
            address.setId(addressId);
            orders.setAddress(address);
            orders.setDvds(dvdCollection);
            LocalDate todayDate = LocalDate.now();
            orders.setOrderDate(todayDate);
            System.out.println(todayDate);
            if (customerService.addOrder(orders)) {
                modelAndView.addObject("status","success");
                modelAndView.addObject("message",  
                    DvdConstants.MSG_ADD_ORDER_SUCCESS);
            } else {
                modelAndView.addObject("status","fail");
                modelAndView.addObject("message",
                    DvdConstants.MSG_ADD_ORDER_FAIL);
            }
            modelAndView.setViewName("CustomerOperations");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return viewOrdersList(request);
    }

   @RequestMapping(value="myAccount",method = RequestMethod.POST)
    public ModelAndView modifyAccount(HttpServletRequest request) {
        HttpSession session=request.getSession(false);  
        ModelAndView modelAndView = new ModelAndView();
        Integer id = (Integer)session.getAttribute("userid");  
        try {
            Customer customer = customerService.getCustomerById
                (id, Boolean.TRUE);
            modelAndView.addObject("customer",customer);
            modelAndView.addObject("newAddress", new Address());
            modelAndView.setViewName("CustomerUpdate");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

   @RequestMapping(value="updateCustomer",method = RequestMethod.POST)
    public ModelAndView updateCustomerDetails(@ModelAttribute("customer")
            Customer customer, HttpServletRequest request ) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session=request.getSession(false);  
System.out.println(session.getAttribute("userid"));
        Integer id = (Integer)session.getAttribute("userid");  
        try {
            customer.setId(id);
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject("status","success");
                modelAndView.addObject("message",
                    "Customer updated Successfully");
            } else {
                modelAndView.addObject("status","fail");
                modelAndView.addObject("message","failed to update customer");
            }
            Customer customerNew = customerService.getCustomerById
                (id, Boolean.TRUE);
            modelAndView.addObject("customer",customerNew);
            modelAndView.addObject("newAddress", new Address());
            modelAndView.setViewName("CustomerUpdate");
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

   @RequestMapping(value="updateAddress",method = RequestMethod.POST)
    public ModelAndView updateAddress(@ModelAttribute("address") 
            Address address, HttpServletRequest request ) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session=request.getSession(false);  
        Integer id = (Integer)session.getAttribute("userid"); 
        try {
            Integer addressId  = Integer.parseInt(request.getParameter
                ("addressId"));
            address.setId(addressId);
            if (!customerService.orderExists(id, address.getId())) {
                if (customerService.updateAddress(address)) {
                    modelAndView.addObject("status","fail");
                    modelAndView.addObject("message",
                        "Address Updated successfully...");
                } else {
                    modelAndView.addObject("status","fail");
                    modelAndView.addObject("message","failed to update address");
                }
            } else {
                modelAndView.addObject("status","fail");
                modelAndView.addObject("message",
                    "Cannot update address.. some orders placed this address");
            }
            Customer customer = customerService.getCustomerById
                (id, Boolean.TRUE);
            modelAndView.addObject("customer",customer);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("newAddress", new Address());
        modelAndView.setViewName("CustomerUpdate");
        return modelAndView;
    }

    @RequestMapping(value="addAddress",method = RequestMethod.POST)
    public ModelAndView addAddress(@ModelAttribute("newAddress")
            Address address, HttpServletRequest request ) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session=request.getSession(false);
        Integer id = (Integer)session.getAttribute("userid"); 

        try {
            if (customerService.addAddress(address)) {
                modelAndView.addObject("status","success");
                modelAndView.addObject("message",
                    "New Address inserted successfully...");
            } else {
                modelAndView.addObject("status","fail");
                modelAndView.addObject("message", "Failed to insert...");
            }
            Customer customer = customerService.getCustomerById
                (id, Boolean.TRUE);
            modelAndView.addObject("customer",customer);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.setViewName("CustomerUpdate");
        return modelAndView;
    }

    @RequestMapping(value="deleteAddress",method = RequestMethod.POST)
    public ModelAndView deleteAddress(@RequestParam("addressId") 
            Integer addressId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session=request.getSession(false);  
        Integer id = (Integer)session.getAttribute("userid"); 
        try {
            Address address = new Address();
            address.setCustomerId(id);
            address.setId(addressId);     

            if (!customerService.orderExists(id, addressId)) {
                if (customerService.deleteAddress(address)) {
                    modelAndView.addObject("status","success");
                    modelAndView.addObject("message",
                        "Address deleted successfully...");
                } else {
                    modelAndView.addObject("status","fail");
                    modelAndView.addObject("message", 
                        "Failed to delete address...");                
                }
            } else {
                modelAndView.addObject("status","fail");
                modelAndView.addObject("message", 
                    "Can't delete..some orders placed to this address");
            }
            Customer customer = customerService.getCustomerById
                (id, Boolean.TRUE);
            modelAndView.addObject("customer",customer);
        } catch (DvdException e) {
            modelAndView.addObject(DvdConstants.STATUS,DvdConstants.FAIL);
            modelAndView.addObject(DvdConstants.MESSAGE, e.getMessage());
        }
        modelAndView.addObject("newAddress", new Address());
        modelAndView.setViewName("CustomerUpdate");
        return modelAndView;
    }
}
