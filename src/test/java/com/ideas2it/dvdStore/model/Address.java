package com.ideas2it.dvdStore.model;

import com.ideas2it.dvdStore.common.DvdConstants;

/**
 * <p>
 * Address class is a POJO class used to handle the address informations.
 *
 * This class is used to getting the informatios such customer street, city,
 * state, pincode.
 *
 * It is used to setting the user inputs to the specified for variables 
 * 
 * @author Anantharaj
 *
 * </p>
 */
public class Address {  

    private Integer id;
    private Integer customerId;
    private String street;
    private String city;
    private String state;
    private Integer pincode;

    /*
     * <p>
     * getting the private variable id
     *
     * @return Integer
     *         returns the dvd id in Integer format
     * </p>
     */
    public Integer getId() {
        return id;
    } 

    /*
     * <p>
     * getting the private variable customer id
     *
     * @return Integer
     *         returns the customer id in Integer format
     * </p>
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /*
     * <p>
     * getting the private variable street
     *
     * @return String
     *         returns the street in String format
     * </p>
     */
    public String getStreet() {
        return street;
    }

    /*
     * <p>
     * getting the private variable city
     *
     * @return String
     *         returns the city in String format
     * </p>
     */
    public String getCity() {
        return city;
    }

    /*
     * <p>
     * getting the private variable state
     *
     * @return String
     *         returns the city in String format
     * </p>
     */
    public String getState() {
        return state;
    }

    /*
     * <p>
     * getting the private variable pincode
     *
     * @return Integer
     *         returns the pincode in Integer format
     * </p>
     */
    public Integer getPincode() {
        return pincode;
    }

    /*
     *setting the Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     *setting the customer id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /*
     *setting the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /*
     *setting the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /*
     *setting the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /*
     *setting the pincode
     */
    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return new StringBuilder(DvdConstants.LABEL_ADDRESS).append(street)
            .append("\t").append(city).append("\t").append(state)
            .append("\t-\t").append(pincode).toString();
    }

}
