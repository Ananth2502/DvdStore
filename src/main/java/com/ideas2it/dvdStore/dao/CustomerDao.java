package com.ideas2it.dvdStore.dao;

import java.util.ArrayList;
import java.util.Set;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Customer;

/**
 * <p>
 *
 * This interface class is used to insert the new customer, 
 * delete existing customer details from store,
 * update existing customer detail in the dvd store.
 *
 * @author Anantharaj
 *
 * </p>
 */
public interface CustomerDao {

    /** 
     * <p>
     * Adding new customer details to store
     * 
     * @param customer
     *        Needed for inserting this customer details
     *
     * @return boolean
     *        If customer details added to store retuns true, otherwise false
     * </p> 
     */
    public Boolean insertCustomer(Customer customer)  throws DvdException ;

    /**
     * <p>
     * Getting particular customer detail from dvd store based on mobile number.
     *
     * @param mobile
     *        Needed for check the customer details
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        Returns particular customer details form dvd store
     * </p>
     */
    public Customer getCustomerByMobile(String mobile, Boolean status)
        throws DvdException ; 

    /**
     * <p>
     * Getting particular customer detail from dvd store based on customer id.
     *
     * @param id
     *        Needed for check customer detail from store
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        Returns particular customer details form dvd store
     * </p>
     */
    public Customer getCustomerById(Integer id, Boolean status) 
        throws DvdException ;

    /** 
     * <p>
     * Delete existing customer detail in the store, if available
     * 
     * @param customer
     *        Needed for deleting customer.
     *
     * @return Boolean
     *        If customer deleted in store retuns true, otherwise false
     * </p> 
     */
    public Boolean deleteCustomer(Customer customer) throws DvdException ;

    /** 
     * <p>
     * Update existing customer detail in the store, based on updated values
     * 
     * @param customer
     *        Needed for updating customer
     *
     * @return boolean
     *        If customer updated to store retuns true, otherwise false
     * </p> 
     */
    public Boolean updateCustomer(Customer customer) throws DvdException;

    /** 
     * <p>
     * Restoring customer account in the dvd store, if already exists.
     * 
     * @param customer
     *        Needed for restoring customer 
     *
     * @return boolean
     *        If customer account restored to store retuns true, otherwise false
     * </p> 
     */
    public Boolean restoreCustomer(Customer customer) throws DvdException;

    /**
     * <p>
     * Getting all active customer details from dvd store 
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Customer>
     *        Returns set of customer details form dvd store
     * </p>
     */
    public Set<Customer> getCustomers(Boolean status) throws DvdException ; 
}
