package com.ivenxu.addressbook;

import static org.junit.Assert.assertEquals;

import com.ivenxu.addressbook.infrastructure.AddressBookInMemoryRepository;
import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests of {@link AddressBookApplicationService}
 * 
 */
public class AddressBookApplicationServiceIT {

    private AddressBookApplicationService addressBookApplicationService;

    private AddressBookRepository addressBookRepository;

    @Before
    public void setup() {
        addressBookApplicationService = new AddressBookApplicationService();
        addressBookApplicationService.addressBookRepository = new AddressBookInMemoryRepository();
    }
    
    @Test
    public void shouldBeAbleToMaintainContactsInAddressBooks() throws DuplicatedEntityException, NotFoundException {
        final String vipAddressBookName = "VIP Contacts";
        final String silverAddressBookName = "Silver Memebers";
        Contact contact1 = new Contact("Nicolas Cage", "0467 777 888");
        Contact contact2 = new Contact("Jonathan Vincent", "0400 999 888");
        Contact contact3 = new Contact("George Clooney", "0444 666 888");

        AddressBook vipBookAdded = addressBookApplicationService.addAddressBook(vipAddressBookName);
        AddressBook silverBookAdded = addressBookApplicationService.addAddressBook(silverAddressBookName);

        addressBookApplicationService.addContact(vipAddressBookName, contact1);
        addressBookApplicationService.addContact(vipAddressBookName, contact2);
        addressBookApplicationService.addContact(silverAddressBookName, contact2);
        addressBookApplicationService.addContact(silverAddressBookName, contact3);

        AddressBook vipBookInRepository = addressBookApplicationService.findAddressBookByName(vipAddressBookName);
        AddressBook silverBookInRepository = addressBookApplicationService.findAddressBookByName(silverAddressBookName);

        assertEquals("The book in repository should be the some added.", vipBookAdded, vipBookInRepository);
        assertEquals("The book in repository should be the some added.", silverBookAdded, silverBookInRepository);
    }
}