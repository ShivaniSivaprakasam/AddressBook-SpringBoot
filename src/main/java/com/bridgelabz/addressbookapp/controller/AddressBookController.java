package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        log.info("Received request to get all contacts");
        return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        log.info("Received request to get contact by id: {}", id);

        AddressBook contact = addressBookService.getContactById(id);

        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }

        log.error("Contact not found in controller with id: {}", id);
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("Received request to add contact: {}", addressBookDTO);

        AddressBook newContact = addressBookService.addContact(addressBookDTO);

        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable int id, @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Received request to update contact with id: {}", id);

        AddressBook updatedContact = addressBookService.updateContact(id, addressBookDTO);

        if (updatedContact != null) {
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        }

        log.error("Contact not found for update in controller with id: {}", id);
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        log.info("Received request to delete contact with id: {}", id);

        boolean deleted = addressBookService.deleteContact(id);

        if (deleted) {
            return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
        }

        log.error("Contact not found for delete in controller with id: {}", id);
        return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}