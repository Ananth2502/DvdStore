package com.ideas2it.dvdStore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.model.Dvd;
import com.ideas2it.dvdStore.model.Orders;

/**
 * <p>
 * This class is used perform the user provided input validate fuctions.
 * It is used to validate string length, check value is positive or not,
 * check input is with in the range..
 *
 * @author Anantharaj
 * </p>
 */
public class DvdUtils {

    /**
     * Used to check user input length is not more than 30 letters
     *
     * @param userInput
     *        needed for validate length
     *
     * @return boolean
     *        returns true, if userinput is less than 30 letters, 
     *        otherwise returns false
     */
    public Boolean validateLength(String userInput) {
        return (userInput.length() < DvdConstants.LABEL_NAMESPACE);
    }

    /**
     * Used to check user input is positive value or negative value
     *
     * @param userInput
     *        needed for check the range of user input
     *
     * @return Boolean
     *        returns false if userinput is less than zero or  userinput 
     *        is greater than 10, otherwise returns true.
     */
    public Boolean validateRatingRange(float userInput) {
        return (0 < userInput && userInput < DvdConstants.LABEL_MAX_RATING);
    }
    
    /**
     * Used to check user input is positive value or negative value
     *
     * @param userInput
     *        needed for check, which is positive
     *
     * @return boolean
     *        returns true, if user input is positive, otherwise returns false
     */
    public boolean isPositive(float userInput) {
        return ( 0 < userInput );
    }

    /**
     * This method is used to check the user provided category id exists in 
     * the dvd store     
     *
     * @param id
     *        needed for check category in category collections
     *
     * @param categories
     *        needed for check id in category list
     *
     * @return boolean
     *        returns true, if category exists in dvdstore, otherwise false
     *
     */
    public boolean isCategoryExists(int id, Set<Category> categories) {
        for (Category category : categories) {
            if (id == category.getId()) {
               return true;
            }
        }
        return false;
    }

    /**
     * This method is used to check the user provided dvd id exists in 
     * the dvd store     
     *
     * @param id
     *        needed for check dvd in category collections
     *
     * @param dvds
     *        needed for check id in dvd list
     *
     * @return boolean
     *        returns true, if dvd exists in dvdstore, otherwise false
     *
     */
    public boolean isDvdExists(int id, Set<Dvd> dvds) {
        for (Dvd dvd : dvds) {
            if (id == dvd.getId()) {
               return true;
            }
        }
        return false;
    }

    /**
     * This method is used to check the user provided dvd id exists in 
     * the dvd store     
     *
     * @param id
     *        needed for check dvd in category collections
     *
     * @param dvds
     *        needed for check id in dvd list
     *
     * @return Dvd
     *        returns dvd, if dvd exists in dvdstore, otherwise false
     *
     */
    public Dvd isDvdQuantityExists(int id, Set<Dvd> dvds) {
        for (Dvd dvd : dvds) {
            if (id == dvd.getId()) {
               if (0 < dvd.getQuantity()) {
                   return dvd;
               }
            }
        }
        return null;
    }

    /**
     * Used to check user input length is not more than 30 letters
     *
     * @param userInput
     *        needed for validate length
     *
     * @return boolean
     *        returns true, if userinput is less than 30 letters, 
     *        otherwise returns false
     */
    public Boolean validateMobileNumber(String mobileNumber) {

        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    /**
     * This method is used to check the user provided dvd id exists in 
     * the dvd store     
     *
     * @param id
     *        needed for check order id in order list
     *
     * @param customerId
     *        needed for check customerId in order list
     *
     * @return Orders
     *        returns order, if order exists in dvdstore, otherwise false
     *
     */
    public Orders isOrderExists(Integer id, Integer customerId, 
            Set<Orders> orders) {
        for (Orders order : orders) {
///            if (id == order.getId() && customerId == order.getCustomerId()) {
                return order;
   //         }
        }
        return null;
    }







}
