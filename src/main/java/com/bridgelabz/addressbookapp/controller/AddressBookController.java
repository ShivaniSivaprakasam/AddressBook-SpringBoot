package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import jakarta.validation.Valid;
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
        log.info("Fetching all contacts");
        return new ResponseEntity<>(
                addressBookService.getAllContacts(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(
            @PathVariable int id) {

        log.info("Fetching contact {}", id);

        AddressBook contact =
                addressBookService.getContactById(id);

        if (contact != null) {
            return new ResponseEntity<>(
                    contact,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "Contact not found",
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(
            @Valid @RequestBody AddressBookDTO addressBookDTO) {

        log.info("Adding contact");

        return new ResponseEntity<>(
                addressBookService.addContact(addressBookDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @PathVariable int id,
            @Valid @RequestBody AddressBookDTO addressBookDTO) {

        log.info("Updating contact {}", id);

        AddressBook contact =
                addressBookService.updateContact(id,
                        addressBookDTO);

        if (contact != null) {
            return new ResponseEntity<>(
                    contact,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "Contact not found",
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(
            @PathVariable int id) {

        log.info("Deleting contact {}", id);

        boolean deleted =
                addressBookService.deleteContact(id);

        if (deleted) {
            return new ResponseEntity<>(
                    "Deleted Successfully",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "Contact not found",
                HttpStatus.NOT_FOUND
        );
    }
}