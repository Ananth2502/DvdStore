package com.ideas2it.dvdStore.service.impl; 

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.dao.UserDao;
import com.ideas2it.dvdStore.dao.impl.UserDaoImpl;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.service.UserService;
import com.ideas2it.dvdStore.service.CustomerService;
import com.ideas2it.dvdStore.service.impl.CustomerServiceImpl;

/**
 * <p>
 * LoginService class is contains the operations of the customer exists or not 
 * in the dvd store..
 *
 * This class have the methods of the customer login functions
 * </p>
 */
public class UserServiceImpl implements UserService {
    
    private UserDao userDao = new UserDaoImpl();
  
    /** 
     * @(inheritdoc)
     */
     public User userLogin( User user ) throws DvdException {
         return userDao.userLogin(user);
     }

    /** 
     * @(inheritdoc)
     */
     public Boolean addCustomer( Customer customer ) throws DvdException {
         CustomerService customerService = new CustomerServiceImpl();
         return customerService.addCustomer(customer);
     }
}
