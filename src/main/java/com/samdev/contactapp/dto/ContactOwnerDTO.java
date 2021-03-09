package com.samdev.contactapp.dto;

import com.samdev.contactapp.model.Contact;

public class ContactOwnerDTO {
    private String name;
    private String lastName;

    public ContactOwnerDTO() {
    }

    public ContactOwnerDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
