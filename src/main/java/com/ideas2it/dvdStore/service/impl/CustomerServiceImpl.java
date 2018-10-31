package com.ideas2it.dvdStore.service.impl; 

import java.util.Set;
import java.util.List;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.CustomerDao;
import com.ideas2it.dvdStore.dao.impl.CustomerDaoImpl;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Address;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.model.Orders;
import com.ideas2it.dvdStore.service.AddressService; 
import com.ideas2it.dvdStore.service.CustomerService; 
import com.ideas2it.dvdStore.service.DvdService; 
import com.ideas2it.dvdStore.service.OrdersService; 
import com.ideas2it.dvdStore.service.UserService; 
import com.ideas2it.dvdStore.service.impl.AddressServiceImpl; 
import com.ideas2it.dvdStore.service.impl.CategoryServiceImpl; 
import com.ideas2it.dvdStore.service.impl.DvdServiceImpl; 
import com.ideas2it.dvdStore.service.impl.UserServiceImpl;
import com.ideas2it.dvdStore.service.impl.OrdersServiceImpl;
/**
 * <p>
 * CustomerServiceImpl class is contains the operations of the customers
 * such as Insert new customer account, delete existing customer
 * in dvd store, update existing customer details in dvd store, 
 * searching the customer details...
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public class CustomerServiceImpl implements CustomerService {
    
    private CustomerDao customerDao = new CustomerDaoImpl();
  
    /** 
     * @(inheritdoc)
     */
     public Boolean addCustomer( Customer customer ) throws DvdException {
         return customerDao.insertCustomer(customer);
     }

    /** 
     * @(inheritdoc)
     */
    public Customer getCustomerByMobile(String mobile, Boolean status) 
            throws DvdException  {
        return customerDao.getCustomerByMobile(mobile,status);
    }

    /** 
     * @(inheritdoc)
     */
    public Customer getCustomerById(Integer id, Boolean status) 
            throws DvdException  {
        return customerDao.getCustomerById(id, status);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteCustomer(Customer customer) throws DvdException {
        return customerDao.deleteCustomer(customer);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean updateCustomer(Customer customer) throws DvdException {
           Customer completeCustomerdetail = customerDao.getCustomerById
               (customer.getId(), Boolean.TRUE);
           completeCustomerdetail.setName(customer.getName());
           completeCustomerdetail.setMobileNumber(customer.getMobileNumber());
           completeCustomerdetail.setMailId(customer.getMailId());
        return customerDao.updateCustomer(completeCustomerdetail);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean restoreCustomer(Customer customer) throws DvdException {
        return customerDao.restoreCustomer(customer);
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Customer> getCustomers(Boolean status) throws DvdException {
        return customerDao.getCustomers(status);
    }

    /** 
     * @(inheritdoc)
     */
    public Set<Dvd> getDvds(Boolean status) throws DvdException {
        DvdService dvdService = new DvdServiceImpl();
        return dvdService.getDvds(status);
    }

    /** 
     * @(inheritdoc)
     */
     public Boolean addOrder( Orders orders ) throws DvdException {
         OrdersService ordersService = new OrdersServiceImpl();
         return ordersService.addOrder(orders);
     }

    /** 
     * @(inheritdoc)
     */
    public Set<Orders> getOrders(Integer customerId) throws DvdException {
        OrdersService ordersService = new OrdersServiceImpl();
        return ordersService.getOrders(customerId);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean orderExists(Integer customerId, Integer addressId) 
        throws DvdException {
        OrdersService ordersService = new OrdersServiceImpl();
        return ordersService.orderExists(addressId, customerId);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteOrder(Orders orders) throws DvdException {
        OrdersService ordersService = new OrdersServiceImpl();
        return ordersService.deleteOrder(orders);
    }

    /** 
     * @(inheritdoc)
     */
     public List<Address> getAddress( Integer id ) throws DvdException {
         AddressService addressService = new AddressServiceImpl();
         return addressService.getAddress(id);
     }

    /** 
     * @(inheritdoc)
     */
     public Boolean addAddress( Address address ) throws DvdException {
         AddressService addressService = new AddressServiceImpl();
         return addressService.addAddress(address);
     }

    /** 
     * @(inheritdoc)
     */
     public Boolean deleteAddress( Address address ) throws DvdException {
         AddressService addressService = new AddressServiceImpl();
         return addressService.deleteAddress(address);
     }

    /** 
     * @(inheritdoc)
     */
     public Boolean updateAddress( Address address ) throws DvdException {
         AddressService addressService = new AddressServiceImpl();
         return addressService.updateAddress(address);
     }
}

