package com.samdev.contactapp.service;

import java.util.List;
import java.util.Optional;

import com.samdev.contactapp.model.ContactOwner;
import com.samdev.contactapp.repository.ContactOwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactOwnerService {

    @Autowired
    private ContactOwnerRepository contactOwnerRepository;

    public List<ContactOwner> getAllContactOwners() {
        return contactOwnerRepository.findAll();
    }

    public Optional<ContactOwner> findById(int id) {
        return contactOwnerRepository.findById(id);
    }

    public ContactOwner save(ContactOwner ctowner) {
        return contactOwnerRepository.save(ctowner);
    }

    public void delete(int id) {
        contactOwnerRepository.deleteById(id);
    }
}
