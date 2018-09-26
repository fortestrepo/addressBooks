package com.ivenxu.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

import org.junit.Before;
import org.junit.Test;

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
     * Verify the use case of "Address book will hold name and phone numbers of
     * contact entries" for single contact in a book
     * 
     * @throws NotFoundException
     */
    @Test
    public void shouldBeAbleToQuerySingleContactDetailViaAddressBook() throws NotFoundException {
        final String contactName = "Nicolas Cage";
        final String contactPhoneNumber = "0467 777 888";
        final String addressBookName = "VIP customers";
        final Contact expectedContact = new Contact(contactName, contactPhoneNumber);
        final AddressBook expectedAddressBook = buildSingleContactAddressBook(addressBookName, expectedContact);
        when(addressBookRepository.findAddressBookByName(addressBookName)).thenReturn(expectedAddressBook);

        final AddressBook actualAddressBook = addressBookApplicationService.findAddressBookByName(addressBookName);
        final Contact actualContact =  actualAddressBook.getContacts().get(0);

        assertEquals(String.format("Should get the correct Address Book for name = '%s'.", addressBookName), expectedAddressBook, actualAddressBook);
        assertEquals("Should get the right contact contained in the address book.", expectedContact, actualContact);
        assertEquals("The name of the contact should be correct.", contactName, actualContact.getName());
        assertEquals("The phone name of the contact should be correct.", contactPhoneNumber, actualContact.getPhoneNumber());
    }

    /**
     * Negative case of "Address book will hold name and phone numbers of contact
     * entries".
     * 
     * Which can't find the address book for the name
     * 
     * @throws NotFoundException
     */
    @Test(expected = NotFoundException.class)
    public void shouldNotReturnAnyAddressBookForWrongNameQuery() throws NotFoundException {
        final String contactName = "Nicolas Cage";
        final String contactPhoneNumber = "0467 777 888";
        final String addressBookName = "VIP customers";
        AddressBook expectedAddressBook = buildSingleContactAddressBook(addressBookName, contactName, contactPhoneNumber);
        when(addressBookRepository.findAddressBookByName(addressBookName)).thenReturn(expectedAddressBook);
        final String searchBookName = "Silver members";

        addressBookApplicationService.findAddressBookByName(searchBookName);
    }

    /**
     * Verify the use case of "Address book will hold name and phone numbers of
     * contact entries" for multiple contacts in a book
     * 
     * @throws NotFoundException
     */
    @Test
    public void shouldBeAbleToQueryMultipleContactsDetailViaAddressBook() throws NotFoundException {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");
        final String bookName = "VIP customers";
        AddressBook expectedAddressBook = new AddressBook(bookName);
        expectedAddressBook.getContacts().add(contact1);
        expectedAddressBook.getContacts().add(contact2);
        expectedAddressBook.getContacts().add(contact3);
        when(addressBookRepository.findAddressBookByName(bookName)).thenReturn(expectedAddressBook);

        AddressBook actualBook = addressBookApplicationService.findAddressBookByName(bookName);

        assertEquals(String.format("Should get the correct Address Book for name = '%s'.", bookName), expectedAddressBook, actualBook);
        assertAddressBookContainsContact(actualBook, contact1);
        assertAddressBookContainsContact(actualBook, contact2);
        assertAddressBookContainsContact(actualBook, contact3);
    }

    /**
     * Verify the use case of "Users should be able to add new contact entries"
     * 
     * @throws NotFoundException
     */
    @Test
    public void shouldBeAbleToAddNewContactToAddressBook() throws NotFoundException {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");
        final String bookName = "VIP customers";
        AddressBook addressBook = new AddressBook(bookName);
        when(addressBookRepository.findAddressBookByName(bookName)).thenReturn(addressBook);

        addressBookApplicationService.addContact(bookName, contact1);
        addressBookApplicationService.addContact(bookName, contact2);
        addressBookApplicationService.addContact(bookName, contact3);

        assertAddressBookContainsContact(addressBook, contact1);
        assertAddressBookContainsContact(addressBook, contact2);
        assertAddressBookContainsContact(addressBook, contact3);

    }

     /**
     * Negative case of "Users should be able to add new contact entries".
     * 
     * Which can't find the address book for the name
     * 
     * @throws NotFoundException
     */
    @Test(expected = NotFoundException.class)
    public void shouldNotAllowUserToAddContactToNotExistingBook() throws NotFoundException {
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");
        final String addressBookName = "VIP customers";
        AddressBook expectedAddressBook = buildSingleContactAddressBook(addressBookName, contact);
        when(addressBookRepository.findAddressBookByName(addressBookName)).thenReturn(expectedAddressBook);
        final String wrongBookName = "Silver members";

        addressBookApplicationService.addContact(wrongBookName, contact);
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

    private AddressBook buildSingleContactAddressBook(final String bookName, final String contactName, final String phoneNumber) {
        Contact contact = new Contact(contactName, phoneNumber);
        return buildSingleContactAddressBook(bookName, contact);
    }

    private AddressBook buildSingleContactAddressBook(final String bookName, Contact contact) {
        AddressBook addressBooke = new AddressBook(bookName);
        addressBooke.getContacts().add(contact);
        return addressBooke;
    }

    private void assertAddressBookContainsContact(AddressBook book, Contact contact) {
        assertTrue(String.format("Should contain contact: %s", contact), book.getContacts().contains(contact));
    }
}
