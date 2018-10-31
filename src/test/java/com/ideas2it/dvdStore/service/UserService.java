package com.ideas2it.dvdStore.service; 

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.User;
import com.ideas2it.dvdStore.model.Customer;

/**
 * <p>
 * LoginService class is contains the operations of the customer exists or not 
 * in the dvd store..
 *
 * This class have the methods of the customer login functions
 * </p>
 */
public interface UserService {

    /**
     * <p>
     * This method is used to check the user exists or not in the dvd store
     *
     * @param login
     *       needed for check user details to dvdstore
     *    
     * @return Login
     *       returns user, if user exists in dvdstore, Otherwise null
     * </p>
     */
    public User userLogin( User user ) throws DvdException ;


    /**
     * <p>
     * This method is used to passing the customer object to DAO layer 
     * for insert new customer to the store 
     *
     * @param customer
     *       needed for adding customer details to dvdstore
     *    
     * @return Boolean
     *       returns true if customer Object added to dvdstore successfully
     *       Otherwise returns false
     * </p>
     */
    public Boolean addCustomer( Customer customer ) throws DvdException ; 

}
