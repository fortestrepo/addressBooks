package com.ivenxu.addressbook.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for domain object AddressBook
 */
public class AddressBookTest {

    @Test
    public void twoAddressBooksAreEqualWhenHaveTheSameName() {
        final String bookName = "VIP Book";
        AddressBook addressBook1 = new AddressBook(bookName);
        AddressBook addressBook2 = new AddressBook(bookName);

        assertEquals("Objects should be equal.", addressBook1, addressBook2);
        assertEquals("HashCode should be the same.", addressBook1.hashCode(), addressBook2.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void bookNameShouldNotBeNull() {
        new AddressBook(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bookNameShouldNotBeEmpty() {
        new AddressBook(" ");
    }
}