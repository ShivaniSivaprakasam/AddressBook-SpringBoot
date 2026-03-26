package com.bridgelabz.addressbookapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressBookController {

    @GetMapping("/addressbook")
    public String getAddressBookMessage() {
        return "Welcome to Address Book App";
    }
}