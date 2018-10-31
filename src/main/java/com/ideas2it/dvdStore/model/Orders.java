package com.ideas2it.dvdStore.model;

import java.time.LocalDate;
import java.util.Set;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.model.Dvd;

/**
 * <p>
 * Dvd class is a POJO class used to handle the dvd informations.
 *
 * This class is used to getting the informatios such dvd id, name, 
 * price,quantity, rating, release date of the dvd
 *
 * It is used to setting the user inputs to the specified for variables 
 * 
 * @author Anantharaj
 *
 * </p>
 */
public class Orders {

    private Integer id;
    private Float totalPrice;
    private LocalDate orderDate;
    private Set<Dvd> dvds;
    private Address address;
    private Customer customer;

    /*
     * <p>
     * getting the private variable id
     *
     * @return Integer
     *         returns the order id in Integer format
     * </p>
     */
    public Integer getId() {
        return id;
    }

    /*
     * <p>
     * getting the private variable price
     *
     * @return Float
     *         returns the total price of dvds in float format    
     * </p>
     */
    public Float getTotalPrice() {
        return totalPrice;
    }

    /*
     * <p>
     * getting the private variable orderDate
     *
     * @return LocalDate
     *         returns the dvd order date in Localdate format    
     * </p>
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /*
     * <p>
     * getting the private variable dvds.
     *
     * @return Set<Dvd>
     *         returns set of dvds
     * </p>
     */
    public Set<Dvd> getDvds() {
        return dvds;
    }

    /*
     * <p>
     * getting the private variable address.
     *
     * @return Address
     *         returns customer address
     * </p>
     */
    public Address getAddress() {
        return address;
    }


    /*
     * <p>
     * getting the private variable customer.
     *
     * @return Customer
     *         returns customer deatils
     * </p>
     */
    public Customer getCustomer() {
        return customer;
    }

    /*
     *setting the order Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     *setting the total price of dvds
     */
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /*
     *setting the dvd order date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /*
     *setting the set of dvd
     */
    public void setDvds(Set<Dvd> dvds) {
        this.dvds = dvds;
    }

    /*ss
     *setting the set of dvd
     */
    public void setAddress(Address address) {
        this.address = address;
    }


    /*
     *setting the set of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return new StringBuilder(DvdConstants.LABEL_ORDERID).append(id)
            .append("\t").append(DvdConstants.LABEL_TOTALPRICE)
            .append(totalPrice).append("\t")
            .append(DvdConstants.LABEL_ORDERDATE).append(orderDate)
            .append("\n").toString();
    }

}
