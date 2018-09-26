package com.ivenxu.addressbook;

import com.ivenxu.addressbook.model.AddressBook;
import com.ivenxu.addressbook.model.Contact;

/**
 * Application Service of Address Book
 *
 */
public class AddressBookApplicationService {
    AddressBookRepository addressBookRepository; // real world this should be injected

    /**
     * Return address book object by searching for {@name}
     * 
     * @param name 
     * @return the address book having the name
     */
    public AddressBook findAddressBookByName(String name) throws NotFoundException {
        AddressBook addressBook = addressBookRepository.findAddressBookByName(name);
        if (addressBook != null) {
            return addressBook;
        } else {
            throw new NotFoundException(String.format("Cannot find Address Book for name : '%s'.", name));
        }
    }

    /**
     * Add a contact to a particular {@link Contact} which has specified name.
     * 
     * @param addressBookName
     * @param contact
     * @throws NotFoundException
     */
	public void addContact(String addressBookName, Contact contact) throws NotFoundException {
        AddressBook addressBook = findAddressBookByName(addressBookName);
        addressBook.add(contact);
	}
}
