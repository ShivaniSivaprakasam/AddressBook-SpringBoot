package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private List<AddressBook> addressBookList = new ArrayList<>();
    private int contactIdCounter = 3;

    public AddressBookService() {
        addressBookList.add(new AddressBook(1, "Shivani", "Chennai", "Tamil Nadu"));
        addressBookList.add(new AddressBook(2, "Rahul", "Bangalore", "Karnataka"));
    }

    public List<AddressBook> getAllContacts() {
        return addressBookList;
    }

    public AddressBook getContactById(int id) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public AddressBook addContact(AddressBookDTO addressBookDTO) {
        AddressBook newContact = new AddressBook(
                contactIdCounter++,
                addressBookDTO.getName(),
                addressBookDTO.getCity(),
                addressBookDTO.getState()
        );
        addressBookList.add(newContact);
        return newContact;
    }

    public AddressBook updateContact(int id, AddressBookDTO addressBookDTO) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                contact.setName(addressBookDTO.getName());
                contact.setCity(addressBookDTO.getCity());
                contact.setState(addressBookDTO.getState());
                return contact;
            }
        }
        return null;
    }

    public boolean deleteContact(int id) {
        for (int i = 0; i < addressBookList.size(); i++) {
            if (addressBookList.get(i).getId() == id) {
                addressBookList.remove(i);
                return true;
            }
        }
        return false;
    }
}