package com.ideas2it.dvdStore.model;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

import com.ideas2it.dvdStore.common.DvdConstants;
import com.ideas2it.dvdStore.model.Category;
import com.ideas2it.dvdStore.utils.DateUtils;

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
public class Dvd {  

    private Integer id;
    private String name;
    private Float price;
    private Integer quantity;
    private Float rating;
    private String language;
    private LocalDate releaseDate;    
    private Set<Category> categories;
    private Boolean status;

    /*
     * This is the empty constructor of Dvd Class
     */
    public Dvd() {}

    /**
     * This is the copy constructor of Dvd class
     */
    public Dvd(Dvd dvd) {
        this.id = dvd.getId();
        this.name = dvd.getName();
        this.price = dvd.getPrice();
        this.quantity = dvd.getQuantity();
        this.rating = dvd.getRating();
        this.language = dvd.getLanguage();
        this.releaseDate = dvd.getReleaseDate();
        this.categories = dvd.getCategories();
        this.status = dvd.getStatus();
    }
    
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
     * getting the private variable price
     *
     * @return Float
     *         returns the dvd price in float format    
     * </p>
     */
    public Float getPrice() {
        return price;
    }

    /*
     * <p>
     * getting the private variable quantity
     *
     * @return Integer
     *         returns the dvd quantity in Integer format    
     * </p>
     */
    public Integer getQuantity() {
        return quantity;
    }

    /*
     * <p>
     * getting the private variable id
     *
     * @return float
     *         returns the dvd rating in float format    
     * </p>
     */
    public Float getRating() {
        return rating;
    }

    /*
     * <p>
     * getting the private variable id
     *
     * @return Integer
     *         returns the dvd id    
     * </p>
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /*
     * <p>
     * getting the private variable language
     *
     * @return String
     *         returns the dvd language in String format
     * </p>
     */
    public String getLanguage() {
        return language;
    }

    /*
     * <p>
     * getting the private variable status
     *
     * @return String
     *         returns the dvd status 
     * </p>
     */
    public Boolean getStatus() {
        return status;
    }

    /*
     * <p>
     * getting the private variable categories.
     *
     * @return List<Category>
     *         returns set of category in String format
     * </p>
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /*
     *setting the dvd name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     *setting the Dvd Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     *setting the Dvd price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /*
     *setting the quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /*
     *setting the dvd rating
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /*
     *setting the dvd release date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /*
     *setting the dvd language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /*
     *setting the dvd status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /*
     *setting the list of categories
     */
    public  void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        DateUtils dateUtils = new DateUtils();
        return new StringBuilder(DvdConstants.LABEL_DVDID).append(id)
            .append("\t").append(DvdConstants.LABEL_NAME).append(name)
            .append("\t").append(DvdConstants.LABEL_PRICE).append(price)
            .append("\t").append(DvdConstants.LABEL_QTY).append(quantity)
            .append("\t").append(DvdConstants.LABEL_RATING).append(rating)
            .append("\t").append(DvdConstants.LABEL_LANGUANGE).append(language)
            .toString();
    }

}
