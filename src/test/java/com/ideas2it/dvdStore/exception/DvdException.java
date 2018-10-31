package com.ideas2it.dvdStore.exception;

import java.lang.Exception;

/**
 *<p>
 *
 * DvdException class is the custom excepyion calss is used handle exceptions.
 *
 * @author Anantharaj
 *
 *</p>
 */
public class DvdException extends Exception {

    /**
     *<p>
     * DvdException constructor is parameterized constructor which is used to 
     * initialize the custom exception class
     *
     * @param message
     *        this is the user defined message to dispaly, if exception occur.
     * @param cause
     *        this is the cause of the exception
     *</p>
     */
    public DvdException(String message) {
        super(message);
    }

    /**
     *<p>
     * DvdException constructor is parameterized constructor which is used to 
     * initialize the custom exception class
     *
     * @param message
     *        this is the user defined message to dispaly, if exception occur.
     * @param cause
     *        this is the cause of the exception
     *</p>
     */
    public DvdException(String message, Throwable cause) {
        super(message, cause);
    }

}

