package com.ideas2it.dvdStore.service; 

import java.util.Set;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.Orders;

/**
 * <p>
 * OrdersService class is contains the operations of the dvd orders
 * such as Insert new order to dvd store, delete existing dvd order detail
 * in dvd store, display the dvd order details...
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public interface OrdersService {
    
    /**
     *
     * <p>
     * This method is used to passing the orders object to DAO layer 
     * for insert new dvd order.
     *
     * @param dvd
     *       needed for passing dvd object to DAO layer
     *    
     * @return Boolean
     *       returns true if order has been placed successfully
     *       Otherwise returns false
     * </p>
     *
     */
    public Boolean addOrder( Orders orders ) throws DvdException ; 

    /**
     * <p>
     * This method is used to get all dvd details of the customer purchased dvds
     * 
     * @param customerId
     *        needed for get which customer ordered dvds 
     *
     * @return Set<Orders>
     *        returns order details of customer
     * </p>
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
     * @return Booleam
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


}
