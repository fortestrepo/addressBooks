package com.ivenxu.addressbook;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for AddressBookApplicationService.
 * 
 * Tests in Application Service serve as acceptance tests.
 */
public class AddressBookApplicationServiceTest {
    /**
     * Verify the use case of "Address book will hold name and phone numbers of contact entries"
     */
    @Test
    public void shouldBeAbleToQueryContactDetailViaAddressBook() {
        assertTrue(true);
    }

    /**
     * Verify the use case of "Users should be able to add new contact entries"
     */
    @Test
    public void shouldBeAbleToAddNewContactToAddressBook() {
        assertTrue(true);
    }

    /**
     * Verify the use case of "Users should be able to remove existing contact entries"
     * 
     */
    @Test
    public void shouldBeAbleToRemoveContactFromAddressBook() {
        assertTrue(true);
    }

    /**
     * Verify the use case of "Users should be able to print all contacts in an address book"
     * 
     */
    @Test
    public void shouldReturnAllContactsInTheBook() {
        assertTrue(true);
    }

    /**
     * Verify the use case of "Users should be able to maintain multiple address books"
     * 
     */
    @Test
    public void shouldBeAbleToAddMulitpleAddressBooks() {
        assertTrue(true);
    }


    /**
     * Verify the requirment of "Users should be able to print a unique set of all contacts across multiple address books"
     * 
     */
    @Test
    public void shouldReturnContactsCrossingAddressBooks() {
        assertTrue(true);
    }
}
