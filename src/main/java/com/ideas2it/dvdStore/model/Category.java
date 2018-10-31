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
public class Category {
  
    private Integer id;
    private String name;
    private Set<Dvd> dvds;
    private Boolean status;

    /**
     * <p>
     * getting the private variable categoryId
     *
     * @return int
     *         returns the dvd id in integer format
     * </p>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>
     * getting the private variable categoryName
     *
     * @return String
     *         returns the category name in String format
     * </p>
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * getting the private variable status
     *
     * @return String
     *         returns the category status
     * </p>
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * <p>
     * getting the set of dvd details
     *
     * @return Set<Dvd>
     *         returns set of dvds 
     * </p>
     */
    public Set<Dvd> getDvds() {
        return dvds;
    }

    /**
     *setting the category name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *setting the category status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *setting the category Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *setting the list of dvds
     */
    public void setDvds(Set<Dvd> dvds) {
        this.dvds = dvds;
    }

    @Override
    public String toString() {
        return new StringBuilder(" " +name).toString();
    }

}
