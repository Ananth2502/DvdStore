package com.ideas2it.dvdStore.model;

import com.ideas2it.dvdStore.common.DvdConstants;

/**
 * <p>
 * Login class is a POJO class used to handle the customer login informations.
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
public class User {

    private Integer id;
    private Integer customerId;
    private String userId;
    private String password;
    private String role;

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
     * getting the private variable userId
     *
     * @return String
     *         returns the userId in String format
     * </p>
     */
    public String getUserId() {
        return userId;
    }

    /*
     * <p>
     * getting the private variable city
     *
     * @return String
     *         returns the city in String format
     * </p>
     */
    public String getPassword() {
        return password;
    }

    /*
     * <p>
     * getting the private variable role
     *
     * @return String
     *         returns the role in String format
     * </p>
     */
    public String getRole() {
        return role;
    }

    /*
     *setting the Dvd Id
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
     *setting the userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /*
     *setting the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     *setting the role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
