# Overview
This is an implementation of a coding challenge about an address book user story: 

As a Reece Branch Manager 
I would like an address book application
So that I can keep track of my customer contacts

Acceptance Criteria

* Address book will hold name and phone numbers of contact entries
* Users should be able to add new contact entries
* Users should be able to remove existing contact entries
* Users should be able to print all contacts in an address book
* Users should be able to maintain multiple address books
* Users should be able to print a unique set of all contacts across multiple address books

# Design assumptions
In a real world, we should clarify with the product owner for following things,
* roughly how many contacts in one address book?
* ask more context or use cases of address book object;
* how to identify an address book? Should the name of address book be unique or introduce global identifier?

These are two questions whose answers affect how we are going to model objects. If there's too many contacts per address book, it's not suitable to model the contact as a value object. As for the second point, the purpose of the story is about "keep track of my customer contacts", from this point view, the "Address Book" could be just an attribute or tag of contacts. 

Without the product owner in the coding challege, following assumptions are made,
* there wouldn't be too many contacts per address book, considering the persona of the story is Branch Manager and the number of customer contacts should be limited for a Branch/Store;
* the "address book" is mentioned in several acceptance criteria, it's very likely to be an important concept;
* the name of the address book should be unique.

Therefore, the design is

* address book is an aggregate root;
* and contact is a value object of the aggregate root.

# Build
```
mvn clean install
```
