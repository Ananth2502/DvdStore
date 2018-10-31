package com.ideas2it.dvdStore.service; 

import java.util.Set;
import java.util.List;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Address;
import com.ideas2it.dvdStore.model.Customer;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.Orders;

/**
 * <p>
 * CustomerService class is contains the operations of the customer
 * such as adding new customer, delete existing customer
 * update existing customer details in dvd store, 
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public interface CustomerService {

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

    /**
     * <p>
     * This method is used to get the particular customer detail from store,
     *
     * @param mobile
     *        needed for check the user details
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        returns the customer detail match with mobile number
     * </p>
     */
    public Customer getCustomerByMobile(String mobile, Boolean status) 
        throws DvdException ; 

    /**
     * <p>
     * This method is used to get the particular customer detail from store,
     *
     * @param id
     *        needed for check customer is available or not
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Customer
     *        returns the customer detail match with customer id.
     * </p>
     */
    public Customer getCustomerById(Integer id, Boolean status) 
        throws DvdException ; 

    /**
     * <p>
     * It passes the customer object to the dao to delete customer from store
     *
     * @param customer
     *      Needed for which customer you want to delete
     *
     * @returns Boolean 
     *      it  returns true, if customer delete from store, otherwise false.
     * </p>
     */
    public Boolean deleteCustomer(Customer customer) throws DvdException ; 

    /**
     * <p>
     * This method is used to passing the updated customer detail to the list
     *        
     * @param customer
     *        Needed for updated customer deatils
     * 
     * @return Boolean 
     *        Returns true if modify the existing customer, otherwise false.
     * </p>
     */
    public Boolean updateCustomer(Customer customer) throws DvdException;

    /**
     * <p>
     * This method is used to restoring the customer detail, if  mobile number 
     * is exists in customer account
     *        
     * @param customer
     *        Needed for restoring customer
     * 
     * @return Boolean 
     *        Returns true if restoring customer account, otherwise false.
     * </p>
     */
    public Boolean restoreCustomer(Customer customer) throws DvdException;

    /**
     * <p>
     * This method is used to get all active customer details from store 
     * 
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Customer>
     *        returns the set of customer details from store
     * </p>
     *
     */
    public Set<Customer> getCustomers(Boolean status) throws DvdException ; 


    /**
     * <p>
     * This method is used to get all active dvd details from dvd store 
     * 
     * @param status
     *        needed for check active or inactive
     *
     * @return Set<Dvd>
     *        returns the set of dvd details from store
     * </p>
     *
     */
    public Set<Dvd> getDvds(Boolean status) throws DvdException ; 


    /**
     * <p>
     * This method is used to passing the order object to DAO layer 
     * for insert new dvd order .
     *
     * @param orders
     *       needed for adding order details
     *    
     * @return Boolean
     *       returns true if order has been placed successfully
     *       Otherwise returns false
     * </p>
     */
    public Boolean addOrder(Orders orders) throws DvdException ;

    /**
     * <p>
     * This method is used to get all dvd details of the customer purchased dvds
     * 
     * @param customerId
     *        needed for get which customer ordered dvds 
     *
     * @return Set<Orders>
     *        returns order details of the customer
     * </p>
     *
     */
    public Set<Orders> getOrders(Integer customerId) throws DvdException ; 

    /**
     * <p>
     * This method is used to get order details based on address ID..
     * 
     * @param addressId
     *        needed for which address order details
     *
     * @param customerId
     *        needed for get which customer ordered dvds 
     *
     * @return Boolean
     *        returns true, if order exists, otherwise false
     * </p>
     *
     */
    public Boolean orderExists(Integer customerId, Integer addressId)
        throws DvdException ; 

    /**
     * <p>
     * It is used delete placed dvd orders based on order id and customer id
     *
     * @param orders
     *      Needed for which customer order you want to delete
     *    
     * @returns Boolean 
     *      it  returns true, if order delete from store, otherwise false.
     * </p>
     */
    public Boolean deleteOrder(Orders orders) throws DvdException ; 

    /**
     * <p>
     * This method is used to get all address details of the customer
     * 
     * @param id
     *        needed for get which customer address
     *
     * @return Set<Address>
     *        returns the set of address details of the customer
     * </p>
     *
     */
    public List<Address> getAddress(Integer id) throws DvdException ;

    /**
     * <p>
     * This method is used to create a new delievery address of a customer..
     *
     * @param address
     *       needed for adding customer address details to dvdstore
     *    
     * @return Boolean
     *       returns true if address Object added to dvdstore successfully
     *       Otherwise returns false
     * </p>
     */
    public Boolean addAddress( Address address ) throws DvdException ;

    /**
     * <p>
     * This method is used to delete delievery address of a customer..
     *
     * @param address
     *       needed for deleting customer address details to dvdstore
     *    
     * @return Boolean
     *       returns true if address Object deleted from dvdstore successfully
     *       Otherwise returns false
     * </p>
     */
    public Boolean deleteAddress( Address address ) throws DvdException ;

    /**
     * <p>
     * This method is used to update address of a customer..
     *
     * @param address
     *       needed for updating customer address details
     *    
     * @return Boolean
     *       returns true if customer address updated successfully
     *       Otherwise returns false
     * </p>
     */
    public Boolean updateAddress( Address address ) throws DvdException ;
}

