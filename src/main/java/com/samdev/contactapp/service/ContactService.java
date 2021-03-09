package com.samdev.contactapp.service;

import java.util.List;
import java.util.Optional;

import com.samdev.contactapp.model.Contact;
import com.samdev.contactapp.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> getContactsByOwner(int id) {
        return contactRepository.findByOwnerId(id);
    }

    public Optional<Contact> findById(int id) {
        return contactRepository.findById(id);
    }

    public Contact save(Contact ct) {
        return contactRepository.save(ct);
    }

    public void delete(int id) {
        contactRepository.deleteById(id);
    }
}
