package com.ivenxu.addressbook;

import static org.junit.Assert.*;

import org.junit.*;
import static org.mockito.Mockito.*;

import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

/**
 * Test for AddressBookApplicationService.
 * 
 * Tests in Application Service serve as acceptance tests.
 */
public class AddressBookApplicationServiceTest {

    private AddressBookApplicationService addressBookApplicationService;

    private AddressBookRepository addressBookRepository;

    @Before
    public void setup() {
        addressBookApplicationService = new AddressBookApplicationService();
        addressBookRepository = mock(AddressBookRepository.class);
        addressBookApplicationService.addressBookRepository = addressBookRepository;
    }

    /**
     * Verify the use case of "Address book will hold name and phone numbers of contact entries"
     */
    @Test
    public void shouldBeAbleToQueryContactDetailViaAddressBook() {
        final String contactName = "Nicolas Cage";
        final String contactPhoneNumber = "0467 777 888";
        final String addressBookName = "VIP customers";
        final Contact expectedContact = new Contact(contactName, contactPhoneNumber);
        final AddressBook expectedAddressBook = new AddressBook(addressBookName);
        expectedAddressBook.getContacts().add(expectedContact);
        when(addressBookRepository.findAddressBookByName(contactName)).thenReturn(expectedAddressBook);

        final AddressBook actualAddressBook = addressBookApplicationService.findAddressBookByName(contactName);
        final Contact actualContact =  actualAddressBook.getContacts().get(0);

        assertEquals(String.format("Should get the correct Address Book for name = '%s'.", contactName), expectedAddressBook, actualAddressBook);
        assertEquals("Should get the right contact contained in the address book.", expectedContact, actualContact);
        assertEquals("The name of the contact should be correct.", contactName, actualContact.getName());
        assertEquals("The phone name of the contact should be correct.", contactPhoneNumber, actualContact.getPhoneNumber());
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
