package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        AddressBook contact = addressBookService.getContactById(id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBook addressBook) {
        return new ResponseEntity<>(addressBookService.addContact(addressBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable int id, @RequestBody AddressBook updatedAddressBook) {
        AddressBook contact = addressBookService.updateContact(id, updatedAddressBook);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        boolean deleted = addressBookService.deleteContact(id);
        if (deleted) {
            return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}