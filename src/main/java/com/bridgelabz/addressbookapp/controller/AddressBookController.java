package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.model.AddressBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private List<AddressBook> addressBookList = new ArrayList<>();

    public AddressBookController() {
        addressBookList.add(new AddressBook(1, "Shivani", "Chennai", "Tamil Nadu"));
        addressBookList.add(new AddressBook(2, "Rahul", "Bangalore", "Karnataka"));
    }

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        return new ResponseEntity<>(addressBookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                return new ResponseEntity<>(contact, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBook addressBook) {
        addressBookList.add(addressBook);
        return new ResponseEntity<>(addressBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable int id, @RequestBody AddressBook updatedAddressBook) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId() == id) {
                contact.setName(updatedAddressBook.getName());
                contact.setCity(updatedAddressBook.getCity());
                contact.setState(updatedAddressBook.getState());
                return new ResponseEntity<>(contact, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        for (int i = 0; i < addressBookList.size(); i++) {
            if (addressBookList.get(i).getId() == id) {
                addressBookList.remove(i);
                return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}