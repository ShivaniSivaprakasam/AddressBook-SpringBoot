package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> getAllContacts() {
        log.info("Fetching all contacts");
        List<AddressBook> contacts = addressBookService.getAllContacts();
        ResponseDTO responseDTO = new ResponseDTO("Fetched all contacts successfully", contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getContactById(@PathVariable int id) {
        log.info("Fetching contact {}", id);
        AddressBook contact = addressBookService.getContactById(id);

        if (contact != null) {
            ResponseDTO responseDTO = new ResponseDTO("Fetched contact successfully", contact);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        ResponseDTO responseDTO = new ResponseDTO("Contact not found", null);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Adding contact");
        AddressBook newContact = addressBookService.addContact(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Contact added successfully", newContact);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateContact(@PathVariable int id,
                                                     @Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Updating contact {}", id);
        AddressBook updatedContact = addressBookService.updateContact(id, addressBookDTO);

        if (updatedContact != null) {
            ResponseDTO responseDTO = new ResponseDTO("Contact updated successfully", updatedContact);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        ResponseDTO responseDTO = new ResponseDTO("Contact not found", null);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteContact(@PathVariable int id) {
        log.info("Deleting contact {}", id);
        boolean deleted = addressBookService.deleteContact(id);

        if (deleted) {
            ResponseDTO responseDTO = new ResponseDTO("Contact deleted successfully", null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        ResponseDTO responseDTO = new ResponseDTO("Contact not found", null);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}