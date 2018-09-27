package com.ivenxu.addressbook;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

/**
 * Custom asserts for address book
 * 
 */
public final class AddressBookCustomAsserts {

    /**
     *  Assert the address book contains the contact.
     * 
     * @param book {@link AddressBook}
     * @param contact {@link Contact}
     */
    public static void assertAddressBookContainsContact(AddressBook book, Contact contact) {
        assertTrue(String.format("Should contain contact: %s", contact), book.getContacts().contains(contact));
    }

    /**
     * Assert the address book does not contain the contact.
     * 
     * @param book {@link AddressBook}
     * @param contact {@link Contact}
     */
    public static void assertAddressBookNotContainsContact(AddressBook book, Contact contact) {
        assertFalse(String.format("Should not contain contact: %s", contact), book.getContacts().contains(contact));
    }

    /**
     * Assert the collection contains the contact
     * @param contacts list of contacts
     * @param contact {@link Contact}
     */
    public static void assertCollectionContainContact(Collection<Contact> contacts, Contact contact) {
        assertTrue(String.format("The list should contain the contact '%s'", contact), contacts.contains(contact));
    }
}