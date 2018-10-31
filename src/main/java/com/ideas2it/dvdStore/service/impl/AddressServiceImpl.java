package com.ideas2it.dvdStore.service.impl; 

import java.util.List;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Address;
import com.ideas2it.dvdStore.service.AddressService; 
import com.ideas2it.dvdStore.dao.AddressDao; 
import com.ideas2it.dvdStore.dao.impl.AddressDaoImpl; 

/**
 * <p>
 * AddressService class is contains the operations of the add customer address
 * delete address, get address,..
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public class AddressServiceImpl implements AddressService {
    
    private AddressDao addressDao = new AddressDaoImpl();

    /** 
     * @(inheritdoc)
     */
     public List<Address> getAddress( Integer id ) throws DvdException {
         return addressDao.getAddress(id);
     }

    /** 
     * @(inheritdoc)
     */
     public Boolean addAddress( Address address ) throws DvdException {
         return addressDao.insertAddress(address);
     }

    /** 
     * @(inheritdoc)
     */
     public Boolean deleteAddress( Address address ) throws DvdException {
         return addressDao.deleteAddress(address);
     }


    /** 
     * @(inheritdoc)
     */
     public Boolean updateAddress( Address address ) throws DvdException {
         return addressDao.updateAddress(address);
     }

}
