package com.ideas2it.dvdStore.service.impl; 

import java.util.Set;

import com.ideas2it.dvdStore.dao.OrdersDao;
import com.ideas2it.dvdStore.dao.impl.OrdersDaoImpl;
import com.ideas2it.dvdStore.service.impl.DvdServiceImpl;
import com.ideas2it.dvdStore.exception.DvdException;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.Orders;
import com.ideas2it.dvdStore.service.OrdersService; 
import com.ideas2it.dvdStore.service.DvdService;
/**
 * <p>
 * OrdersServiceImpl class is contains the operations of the orders
 * such as Insert new ordrer to dvd store, delete existing order details
 * in dvd store, viewing the ordered details...
 *
 * This class have the methods of the DvdStore functions
 * </p>
 */
public class OrdersServiceImpl implements OrdersService {
    
    private OrdersDao ordersDao = new OrdersDaoImpl();
  
    /** 
     * @(inheritdoc)
     */
     public Boolean addOrder(Orders orders) throws DvdException {
         DvdService dvdService = new DvdServiceImpl();
         Set<Dvd> dvds = orders.getDvds();
         Set<Dvd> dvdCollection = dvdService.getDvdsByIds(dvds,Boolean.TRUE);
         float totalPrice = 0;
         for (Dvd dvd : dvdCollection) {
             totalPrice += dvd.getPrice();
         }
         orders.setTotalPrice(totalPrice);
         orders.setDvds(dvdCollection);
         return ordersDao.insertOrder(orders);
     }

    /** 
     * @(inheritdoc)
     */
    public Set<Orders> getOrders(Integer customerId) throws DvdException {
        return ordersDao.getOrders(customerId);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean orderExists(Integer customerId, Integer addressId) 
        throws DvdException {
        return ordersDao.orderExists(addressId, customerId);
    }

    /** 
     * @(inheritdoc)
     */
    public Boolean deleteOrder(Orders orders) throws DvdException {
        Orders order = ordersDao.getOrder(orders.getId());
        return ordersDao.deleteOrder(order);
    }

}
