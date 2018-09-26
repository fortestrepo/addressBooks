package com.ivenxu.addressbook.model;

import java.util.*;

/**
 * Domain model of Address Book
 * 
 */
public class AddressBook {

    private List<Contact> contacts = new ArrayList<>();
    private String bookName;

    public AddressBook(String bookName) {
        this.setBookName(bookName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AddressBook)) {
            return false;
        }
        AddressBook addressBook = (AddressBook) o;
        return Objects.equals(bookName, addressBook.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookName);
    }

    @Override
    public String toString() {
        return "{" +
            " bookName='" + getBookName() + "'" +
            "}";
    }


    /**
     * Get all contacts in the Address Book
     * 
     * @return the contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }


    /**
     * Get name of the Address Book
     * 
     * @return the bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Set the name of the Address Book
     * 
     * @param bookName the bookName to set
     */
    private void setBookName(String bookName) {
        if (bookName == null || bookName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        this.bookName = bookName;
    }

	public void add(Contact newContact) {
        getContacts().add(newContact);
	}

}