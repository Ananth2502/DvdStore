package com.ideas2it.dvdStore.model;

import java.util.List;
import java.util.Set;

import com.ideas2it.dvdStore.common.DvdConstants;

/**
 * <p>
 * Customer class is a POJO class used to handle the customer informations.
 *
 * This class is used to getting the informatios of the customer such as 
 * name, mobile number, , 
 *
 * It is used to setting the user inputs to the specified for variables 
 * 
 * @author Anantharaj
 *
 * </p>
 */
public class Customer {  

    private Integer id;
    private String name;
    private String mobileNumber;
    private String mailId;
    private List<Address> addresses;
    private User user;
    private Boolean status;

    /*
     * This is the empty constructor of Dvd Class
     */
    public Customer() {}

    /**
     * This is the copy constructor of Dvd class
     */
    public Customer(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.mobileNumber = customer.getMobileNumber();
        this.mailId = customer.getMailId();
        this.addresses = customer.getAddresses();
        this.user = customer.getUser();
        this.status = customer.getStatus();
    }
    
    /*
     * <p>
     * getting the private variable id
     *
     * @return Integer
     *         returns the dvd id in integer format
     * </p>
     */
    public Integer getId() {
        return id;
    }

    /*
     * <p>
     * getting the private variable name
     *
     * @return String
     *         returns the dvd name in String format
     * </p>
     */
    public String getName() {
        return name;
    }

    /*
     * <p>
     * getting the private variable mobile number
     *
     * @return Integer
     *         returns the mobile number in integer format    
     * </p>
     */
    public String getMobileNumber() {
        return mobileNumber;
    }


    /*
     * <p>
     * getting the private variable mail
     *
     * @return Integer
     *         returns the mobile number in integer format    
     * </p>
     */
    public String getMailId() {
        return mailId;
    }

    /*
     * <p>
     * getting the private variable login
     *
     * @return String
     *         returns the customer login details
     * </p>
     */
    public User getUser() {
        return user;
    }

    /*
     * <p>
     * getting the private variable addresses
     *
     * @return String
     *         returns the customer address details
     * </p>
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /*
     * <p>
     * getting the private variable status
     *
     * @return String
     *         returns the customer status 
     * </p>
     */
    public Boolean getStatus() {
        return status;
    }

    /*
     *setting the cunstomer Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     *setting the customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     *setting the quantity
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /*
     *setting the quantity
     */
    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    /*
     *setting the customer address
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /*
     *setting the customer login
     */
    public void setUser(User user) {
        this.user = user;
    }

    /*
     *setting the dvd status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

}
