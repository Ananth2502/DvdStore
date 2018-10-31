package com.ideas2it.dvdStore.logger;

import java.lang.StackTraceElement;
import org.apache.log4j.Logger;

/**
 * <p>
 * This is the custom logger class to log the exceptions..
 * It is used to print the log messages in the log file..
 * </p>
 */
public class DvdLogger {

    static final Logger logger = Logger.getLogger(DvdLogger.class.getName()); 

    public void error(String message, Exception e) {
        logger.error(message, e);
    }

    public void debug(Exception e) {
        logger.debug(e);
    }

}
