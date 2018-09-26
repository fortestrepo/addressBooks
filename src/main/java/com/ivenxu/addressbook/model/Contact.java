package com.ivenxu.addressbook.model;

import java.util.Objects;

/**
 * Domain model of Contact
 * 
 */
public class Contact {

    private String name;
    private String phoneNumber;

    /**
     * Constructor 
     * 
     * @param name contact name
     * @param phoneNumber contact phone number
     */
    public Contact(String name, String phoneNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            "}";
    }

    /**
     * Get phone number of the contact
     * 
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set phone number of the contact
     * 
     * @param phoneNumber the phoneNumber to set
     */
    private void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone Number is required.");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the name of contact
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of contact
     * 
     * @param name the name to set
     */
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is requried.");
        }
        this.name = name;
    }

}