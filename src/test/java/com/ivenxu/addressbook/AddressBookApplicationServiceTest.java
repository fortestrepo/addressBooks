package com.ivenxu.addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test for AddressBookApplicationService.
 * 
 * Tests in Application Service serve as acceptance tests.
 */
@RunWith(MockitoJUnitRunner.class)
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
        final AddressBook expectedAddressBook = mockAddressBookRepositoryWithSingleBook(addressBookName, expectedContact);

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
        final String addressBookName = "VIP customers";
        final String searchBookName = "Silver members";
        mockAddressBookRepositoryWithSingleBook(addressBookName);

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
        AddressBook expectedAddressBook = mockAddressBookRepositoryWithSingleBook(bookName, contact1, contact2, contact3);

        AddressBook actualBook = addressBookApplicationService.findAddressBookByName(bookName);

        assertEquals(String.format("Should get the correct Address Book for name = '%s'.", bookName), expectedAddressBook, actualBook);
        AddressBookCustomAsserts.assertAddressBookContainsContact(actualBook, contact1);
        AddressBookCustomAsserts.assertAddressBookContainsContact(actualBook, contact2);
        AddressBookCustomAsserts.assertAddressBookContainsContact(actualBook, contact3);
    }

    /**
     * Verify the use case of "Users should be able to add new contact entries"
     * 
     * @throws NotFoundException
     * @throws DuplicatedEntityException
     */
    @Test
    public void shouldBeAbleToAddNewContactToAddressBook() throws NotFoundException, DuplicatedEntityException {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");
        final String bookName = "VIP customers";
        AddressBook addressBook = mockAddressBookRepositoryWithSingleBook(bookName);

        addressBookApplicationService.addContact(bookName, contact1);
        addressBookApplicationService.addContact(bookName, contact2);
        addressBookApplicationService.addContact(bookName, contact3);

        AddressBookCustomAsserts.assertAddressBookContainsContact(addressBook, contact1);
        AddressBookCustomAsserts.assertAddressBookContainsContact(addressBook, contact2);
        AddressBookCustomAsserts.assertAddressBookContainsContact(addressBook, contact3);

    }

     /**
     * Negative case of "Users should be able to add new contact entries".
     * 
     * Scenario:  Which can't find the address book for the name
     * 
     * @throws NotFoundException
     * @throws DuplicatedEntityException
     */
    @Test(expected = NotFoundException.class)
    public void shouldNotAllowUserToAddContactToNotExistingBook() throws NotFoundException, DuplicatedEntityException {
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");
        final String addressBookName = "VIP customers";
        final String wrongBookName = "Silver members";
        mockAddressBookRepositoryWithSingleBook(addressBookName);

        addressBookApplicationService.addContact(wrongBookName, contact);
    }

    /**
     * Negative case of "Users should be able to add new contact entries".
     * 
     * Scenario: Should not allow user to duplicated contact to one book
     * 
     * @throws DuplicatedEntityException
     * @throws NotFoundException
     * 
     */
    @Test(expected = DuplicatedEntityException.class)
    public void shouldNotAllowUserToAddDuplicatedContactToTheSameBook() throws NotFoundException, DuplicatedEntityException {
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");
        final String bookName = "VIP customers";
        mockAddressBookRepositoryWithSingleBook(bookName);

        addressBookApplicationService.addContact(bookName, contact);
        addressBookApplicationService.addContact(bookName, contact);
    }

    /**
     * Verify the use case of "Users should be able to remove existing contact
     * entries"
     * 
     * Scenario: Single address book with single target contact
     * 
     */
    @Test
    public void shouldBeAbleToRemoveContactFromAddressBook() {
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");
        final String bookName = "VIP customers";
        AddressBook addressBook = mockAddressBookRepositoryWithSingleBook(bookName, contact);

        boolean removedSuccess = addressBookApplicationService.removeContact(bookName, contact);

        assertTrue("Contact should be remove successfully.", removedSuccess);
        AddressBookCustomAsserts.assertAddressBookNotContainsContact(addressBook, contact);
    }

    /**
     * Verify the use case of "Users should be able to remove existing contact
     * entries"
     * 
     * Scenario: Address book doesn't have the target contact to remove
     */
    @Test
    public void targetContactNotInAddressBook() {
        Contact contact = new Contact("Nicolas Cage", "0467 777 888");
        final String bookName = "VIP customers";
        mockAddressBookRepositoryWithSingleBook(bookName);

        boolean removedSuccess = addressBookApplicationService.removeContact(bookName, contact);

        assertFalse("Contact should be remove successfully.", removedSuccess);
   
    }

    /**
     * Verify the use case of "Users should be able to remove existing contact
     * entries"
     * 
     * Scenario: Address book has the target contact and other contacts
     * 
     */
    @Test
    public void shouldRemoveTargetContactSuccessfullyAndKeekRemainingNoChange() {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        final String bookName = "VIP customers";
        AddressBook addressBook = mockAddressBookRepositoryWithSingleBook(bookName, contact1, contact2);

        boolean removedSuccess = addressBookApplicationService.removeContact(bookName, contact1);

        assertTrue("Contact should be remove successfully.", removedSuccess);
        AddressBookCustomAsserts.assertAddressBookNotContainsContact(addressBook, contact1);
        AddressBookCustomAsserts.assertAddressBookContainsContact(addressBook, contact2);
   
    }

    /**
     * Verify the use case of "Users should be able to print all contacts in an
     * address book"
     * 
     */
    @Test
    public void shouldReturnAllContactsInTheBook() {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");
        final String bookName = "VIP customers";
        mockAddressBookRepositoryWithSingleBook(bookName, contact1, contact2, contact3);

        List<Contact> contacts = addressBookApplicationService.getContacts(bookName);
        AddressBookCustomAsserts.assertCollectionContainContact(contacts, contact1);
        AddressBookCustomAsserts.assertCollectionContainContact(contacts, contact2);
        AddressBookCustomAsserts.assertCollectionContainContact(contacts, contact3);

    }

    /**
     * Verify the use case of "Users should be able to maintain multiple address
     * books"
     * 
     * Scenario: add multiple address books
     * 
     * @throws NotFoundException
     * @throws DuplicatedEntityException
     * 
     */
    @Test
    public void shouldBeAbleToAddMulitpleAddressBooks() throws NotFoundException, DuplicatedEntityException {
        final String vipBookName = "VIP Customers";
        final String silverBookName = "Silver Members";
        AddressBook actualVipBook = addressBookApplicationService.addAddressBook(vipBookName);
        AddressBook actualSilverBook = addressBookApplicationService.addAddressBook(silverBookName);
        mockAddressBookRepositoryWithSingleBook(vipBookName);
        mockAddressBookRepositoryWithSingleBook(silverBookName);

        assertNotNull("Should add address book successfully.", actualVipBook);
        assertNotNull("Should add address book successfully.", actualSilverBook);
        assertEquals("The address book name should be correct.", vipBookName, actualVipBook.getBookName());
        assertEquals("The address book name should be correct.", silverBookName, actualSilverBook.getBookName());
        AddressBook expectedVipBook = addressBookApplicationService.findAddressBookByName(vipBookName);
        AddressBook expectedSilverBook = addressBookApplicationService.findAddressBookByName(silverBookName);
        assertEquals("Should be able to find the book just added.", expectedVipBook, actualVipBook);
        assertEquals("Should be able to find the book just added.", expectedSilverBook, actualSilverBook);

    }

    /**
     * Verify the use case of "Users should be able to maintain multiple address
     * books"
     * 
     * Scenario: not allow to add duplicated address books
     * 
     * @throws NotFoundException
     * @throws DuplicatedEntityException
     * 
     */
    @Test(expected = DuplicatedEntityException.class)
    public void shouldNotAllowDuplicatedAddressBooks() throws NotFoundException, DuplicatedEntityException {
        final String vipBookName = "VIP Customers";
        AddressBook actualVipBook = addressBookApplicationService.addAddressBook(vipBookName);

        assertNotNull("The first book should be added successfully.", actualVipBook);

        mockAddressBookRepositoryWithSingleBook(vipBookName);

        addressBookApplicationService.addAddressBook(vipBookName);
    }


    /**
     * Verify the requirment of "Users should be able to print a unique set of all contacts across multiple address books"
     * 
     * Scenario: multiple contacts in multiple address books but no intersected contacts. 
     * 
     */
    @Test
    public void shouldReturnContactsCrossingAddressBooks() {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");
        final String vipBookName = "VIP Customers";
        final String silverBookName = "Silver Members";
        mockAddressBookRepositoryWithSingleBook(vipBookName, contact1, contact2);
        mockAddressBookRepositoryWithSingleBook(silverBookName, contact3);

        Set<Contact> actualContacts = addressBookApplicationService.getContacts(Arrays.asList(vipBookName, silverBookName));

        assertEquals("The total number of contacts should be correct.", 3, actualContacts.size());
        AddressBookCustomAsserts.assertCollectionContainContact(actualContacts, contact1);
        AddressBookCustomAsserts.assertCollectionContainContact(actualContacts, contact2);
        AddressBookCustomAsserts.assertCollectionContainContact(actualContacts, contact3);
    }

    /**
     * Verify the requirment of "Users should be able to print a unique set of all contacts across multiple address books"
     * 
     * Scenario: multiple contacts in multiple address books WITH intersected contacts. 
     * 
     */
    @Test
    public void shouldReturnContactsCrossingAddressBooksWithDistictedSet() {
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");
        final String vipBookName = "VIP Customers";
        final String silverBookName = "Silver Members";
        mockAddressBookRepositoryWithSingleBook(vipBookName, contact1, contact2);
        mockAddressBookRepositoryWithSingleBook(silverBookName, contact1, contact3);

        Set<Contact> actualContacts = addressBookApplicationService.getContacts(Arrays.asList(vipBookName, silverBookName));

        assertEquals("The total number of contacts should be correct.", 3, actualContacts.size());
        AddressBookCustomAsserts.assertCollectionContainContact(actualContacts, contact1);
        AddressBookCustomAsserts.assertCollectionContainContact(actualContacts, contact2);
        AddressBookCustomAsserts.assertCollectionContainContact(actualContacts, contact3);
    }

     /**
     * Verify the requirment of "Users should be able to print a unique set of all contacts across multiple address books"
     * 
     * Scenario: empty list address book or passing null for search parameter
     * 
     */
    @Test
    public void shouldGetEmptySetWhenQueryOnEmptyBookList() {
        Set<Contact> actualContacts = addressBookApplicationService.getContacts(Collections.emptyList());
        
        assertTrue("Empty query parameter should return empty set.", actualContacts.size() == 0);

        actualContacts = addressBookApplicationService.getContacts((List<String>)null);

        assertTrue("Passing null as query parameter should return empty set.", actualContacts.size() == 0);
    }

    private AddressBook mockAddressBookRepositoryWithSingleBook(final String bookName, Contact... contacts) {
        AddressBook addressBook = new AddressBook(bookName);
        for (Contact contact : contacts) {
            addressBook.getContacts().add(contact);
        }
        when(addressBookRepository.findAddressBookByName(bookName)).thenReturn(addressBook);
        return addressBook;
    }

}
