package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private List<AddressBook> addressBookList = new ArrayList<>();

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

    public AddressBook addContact(AddressBook addressBook) {
        addressBookList.add(addressBook);
        return addressBook;
    }

    public AddressBook updateContact(int id, AddressBook updatedAddressBook) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                contact.setName(updatedAddressBook.getName());
                contact.setCity(updatedAddressBook.getCity());
                contact.setState(updatedAddressBook.getState());
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
