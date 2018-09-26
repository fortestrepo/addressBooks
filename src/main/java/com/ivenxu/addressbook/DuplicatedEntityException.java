package com.ivenxu.addressbook;

/**
 * DuplicatedEntity when add duplicated object to no-duplicated container
 * 
 */
public class DuplicatedEntityException extends Exception {

    private static final long serialVersionUID = 3636933492464491725L;

    public DuplicatedEntityException(String message) {
        super(message);
    }
}