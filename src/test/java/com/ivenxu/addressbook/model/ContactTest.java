package com.ivenxu.addressbook.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for domain object Contact
 * 
 */
public class ContactTest {

    /**
     * Test Contact.equals and hasCode
     */
    @Test
    public void twoObjectsAreEqualAndHaveTheSameHashWhenHaveTheSameProperties() {
        final String name = "Nicolas Cage";
        final String phone = "0466 777 888";
        Contact contact1 = new Contact(name, phone);
        Contact contact2 = new Contact(name, phone);

        assertEquals("Objects should be equal.", contact1, contact2);
        assertEquals("HashCode should be the same.", contact1.hashCode(), contact2.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void contactNameShouldNotBeNull() {
        new Contact(null, "0466 777 888");
    }

    @Test(expected = IllegalArgumentException.class)
    public void contactNameShouldNotBeEmpty() {
        new Contact(" ", "0466 777 888");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void contactPhoneShouldNotBeNull() {
        new Contact("name", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void contactPhoneShouldNotBeEmpty() {
        new Contact("name", " ");
    }
}