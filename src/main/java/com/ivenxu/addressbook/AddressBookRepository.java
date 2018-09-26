package com.ivenxu.addressbook;

import com.ivenxu.addressbook.model.AddressBook;

/**
 * AddressBookRepository
 * 
 */
public interface AddressBookRepository {

    AddressBook findAddressBookByName(String name);

    AddressBook addAddressBook(AddressBook book);
}