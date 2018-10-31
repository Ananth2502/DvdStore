package com.ideas2it.dvdStore.dao;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.User;

/**
 * <p>
 * LoginDao class is contains the operations of the customer exists or not 
 * in the dvd store..
 *
 * This class have the methods of the customer login functions
 * </p>
 */
public interface UserDao {


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
    public User userLogin(User user)  throws DvdException ;


}
