package com.samdev.contactapp.dto;

public class ContactDTO {
    private String name;
    private String lastName;
    private String phoneNumber;
    private int owner;

    public ContactDTO() {
    }

    public ContactDTO(String name, String lastName, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public ContactDTO(String name, String lastName, String phoneNumber, int owner) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

}
