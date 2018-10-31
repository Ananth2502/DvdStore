package com.ideas2it.dvdStore.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ideas2it.dvdStore.common.DvdConstants;

/**
 * <p>
 *
 * This class is used perform all the date operations
 *
 * @author Anantharaj
 *
 * </p>
 */
public class DateUtils { 

    /**
     * Used to calculate period between some date and current date.
     *        
     * @param releaseDate
     *      needed to calculating period between today and some Date
     * 
     * @return String
     *      return message of period between some date and currentdate
     */
    public String calculatePeriod(LocalDate date) {
        LocalDate todayDate = LocalDate.now();
        long period = ChronoUnit.DAYS.between(date,todayDate);
        if (0 == period) {
            return DvdConstants.MSG_LESS_DAY_AGO;
        } else if (1 <= period && 30 >= period) {
            return DvdConstants.LABEL_RELEASED + (int)period + 
                DvdConstants.MSG_DAY_AGO;
        } else if (30 < period && 365 > period) {
            period = period / 30;
            return DvdConstants.LABEL_RELEASED + (int)period + 
                DvdConstants.MSG_MONTH_AGO;
        } else {
            period = period / 365;
            return DvdConstants.LABEL_RELEASED + (int)period +
                DvdConstants.MSG_YEAR_AGO;
        }
    }

    /**
     * Used to check the date is not a future date
     *        
     * @param date
     *      needed to check the date is future date or not
     * 
     * @return boolean
     *      return false, if the date is future date. otherwise true
     */
    public boolean isFutureDate(LocalDate date) {
        LocalDate todayDate = LocalDate.now();
        if (date.isBefore(todayDate) || date.equals(todayDate)) {
            return true;
        }
        return false;
    }

}
