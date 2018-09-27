package com.ivenxu.addressbook;

import java.util.Arrays;

import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

import org.junit.Test;

/**
 * Tests for custom asserts
 */
public class AddressBookCustomAssertsTeest {

    @Test
    public void testAssertAddressBookContainsContact() {
        AddressBook addressbook = new AddressBook("VIP Contacts");
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");
        addressbook.getContacts().add(contact);

        AddressBookCustomAsserts.assertAddressBookContainsContact(addressbook, contact);
    }

    @Test
    public void testassertAddressBookNotContainsContact() {
        AddressBook addressbook = new AddressBook("VIP Contacts");
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");

        AddressBookCustomAsserts.assertAddressBookNotContainsContact(addressbook, contact);
    }

    @Test
    public void testassertCollectionContainContact() {
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");

        AddressBookCustomAsserts.assertCollectionContainContact(Arrays.asList(contact), contact);
    }
}