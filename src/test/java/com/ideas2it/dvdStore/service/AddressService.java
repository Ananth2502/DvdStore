package com.ideas2it.dvdStore.service; 

import java.util.List;

import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Address;

/**
 * <p>
 * AddressService class is contains the operations of the add customer address
 * delete address, get address,..
 *
 * This class have the methods of the customer address functions
 * </p>
 */
public interface AddressService {

    /**
     * <p>
     * This method is used to get all address details of the particular customer
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
