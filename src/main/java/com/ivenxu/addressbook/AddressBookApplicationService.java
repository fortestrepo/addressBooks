package com.ivenxu.addressbook;

import com.ivenxu.addressbook.model.AddressBook;

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
    public AddressBook findAddressBookByName(String name) {
        return addressBookRepository.findAddressBookByName(name);
    }
}
