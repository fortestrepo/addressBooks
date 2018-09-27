package com.ivenxu.addressbook;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @throws DuplicatedEntityException
     */
	public void addContact(String addressBookName, Contact contact) throws NotFoundException, DuplicatedEntityException {
        AddressBook addressBook = findAddressBookByName(addressBookName);
        addressBook.add(contact);
	}

	public boolean removeContact(String bookName, Contact contact) throws NotFoundException {
        AddressBook addressBook = findAddressBookByName(bookName);
        return addressBook.getContacts().remove(contact);
	}

	public List<Contact> getContacts(String bookName) throws NotFoundException {
        AddressBook addressBook = findAddressBookByName(bookName);
        return addressBook.getContacts();
	}

    /**
     * Create a new {@link AddressBook}.
     * 
     * @param bookName the name for the new address book
     * @return the newky created address book
     * @throws DuplicatedEntityException when the bookName is existing already
     */
	public AddressBook addAddressBook(String bookName) throws DuplicatedEntityException {
        if (null == addressBookRepository.findAddressBookByName(bookName)) {
            AddressBook addressBook = new AddressBook(bookName);
            addressBookRepository.addAddressBook(addressBook);
            return addressBook;
        } else {
            throw new DuplicatedEntityException(String.format("Address Book with name '%s' is existing already.", bookName));
        }
    }
    

    /**
     * List distincted contacts in a list of address book.
     * 
     * @param bookNames the book names to query
     * @return merged contacts set
     */
	public Set<Contact> getContacts(List<String> bookNames) {
		if (bookNames != null) {
            return bookNames.stream().map(bookName -> {
                AddressBook addressBook = addressBookRepository.findAddressBookByName(bookName);
                if (addressBook != null) {
                    return new HashSet<>(addressBook.getContacts());
                } else {                    
                    return new HashSet<Contact>();
                }
            }).flatMap(s -> s.stream()).collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }
}
