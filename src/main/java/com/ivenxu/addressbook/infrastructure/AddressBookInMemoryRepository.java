package com.ivenxu.addressbook.infrastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ivenxu.addressbook.AddressBookRepository;
import com.ivenxu.addressbook.model.AddressBook;

/**
 * An in-memory implementation of {@link AddressBookRepository}.
 * 
 */
public class AddressBookInMemoryRepository implements AddressBookRepository {

    private Map<String, AddressBook> addressBookStore = new ConcurrentHashMap<>();

    @Override
    public AddressBook findAddressBookByName(String name) {
        return addressBookStore.get(name);
    }

    @Override
    public AddressBook addAddressBook(AddressBook book) {
        return addressBookStore.put(book.getBookName(), book);
	}
}