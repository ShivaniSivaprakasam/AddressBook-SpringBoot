package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private int contactIdCounter = 1;

    public List<AddressBook> getAllContacts() {
        log.info("Getting all contacts from service");
        if (addressBookList.isEmpty()) {
            log.warn("Address book list is currently empty");
        }
        return addressBookList;
    }

    public AddressBook getContactById(int id) {
        log.info("Searching contact by id: {}", id);
        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                log.info("Contact found with id: {}", id);
                return contact;
            }
        }
        log.error("Contact not found with id: {}", id);
        return null;
    }

    public AddressBook addContact(AddressBookDTO addressBookDTO) {
        log.info("Adding contact: {}", addressBookDTO);

        AddressBook newContact = new AddressBook(
                contactIdCounter++,
                addressBookDTO.getName(),
                addressBookDTO.getCity(),
                addressBookDTO.getState()
        );

        addressBookList.add(newContact);
        log.info("Contact added successfully with id: {}", newContact.getId());
        return newContact;
    }

    public AddressBook updateContact(int id, AddressBookDTO addressBookDTO) {
        log.info("Updating contact with id: {}", id);

        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                contact.setName(addressBookDTO.getName());
                contact.setCity(addressBookDTO.getCity());
                contact.setState(addressBookDTO.getState());

                log.info("Contact updated successfully with id: {}", id);
                return contact;
            }
        }

        log.error("Unable to update. Contact not found with id: {}", id);
        return null;
    }

    public boolean deleteContact(int id) {
        log.info("Deleting contact with id: {}", id);

        for (int i = 0; i < addressBookList.size(); i++) {
            if (addressBookList.get(i).getId() == id) {
                addressBookList.remove(i);
                log.info("Contact deleted successfully with id: {}", id);
                return true;
            }
        }

        log.error("Unable to delete. Contact not found with id: {}", id);
        return false;
    }
}