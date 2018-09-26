package com.ivenxu.addressbook;

/**
 * NotFoundException when there's no entity existing
 * 
 */
public class NotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
    
}