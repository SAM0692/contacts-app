package com.samdev.contactapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.samdev.contactapp.dto.ContactDTO;
import com.samdev.contactapp.exception.ResourceNotFoundException;
import com.samdev.contactapp.mapper.ContactMapper;
import com.samdev.contactapp.model.Contact;
import com.samdev.contactapp.model.ContactOwner;
import com.samdev.contactapp.service.ContactOwnerService;
import com.samdev.contactapp.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactOwnerService contactOwnerService;

    // Get all contacts GET /api/contacts
    @GetMapping(value = "/contacts")
    List<Contact> getAll() {
        return contactService.getAllContacts();
    }

    // Get all contacts by ContactOwner GET /api/owners/{id}/contacts
    @GetMapping(value = "/owners/{id}/contacts")
    List<Contact> getByOwner(@PathVariable("id") int id) {
        return contactService.getContactsByOwner(id);
    }

    // Get single contacts by ID GET /api/contacts/{id}
    @GetMapping(value = "/contacts/{id}")
    ResponseEntity<Contact> getById(@PathVariable("id") int id) {
        Contact ct = contactService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No contact  with id: " + id));

        return ResponseEntity.ok().body(ct);
    }

    // Create new contacts POST /api/contacts
    @PostMapping(value = "/contacts")
    ResponseEntity<?> createContact(@RequestBody ContactDTO inct) {
        Optional<ContactOwner> ctowner = contactOwnerService.findById(inct.getOwner());
        Contact ct = ContactMapper.DtoToEntity(inct, ctowner.get());
        Contact addedct = contactService.save(ct);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Update contacts details PUT /api/contacts/{id}
    @PutMapping(value = "/contacts/{id}")
    ResponseEntity<Contact> updateContact(@PathVariable("id") int id, @RequestBody ContactDTO inct) {
        Optional<ContactOwner> ctowner = contactOwnerService.findById(inct.getOwner());
        Contact ct = contactService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No contact  with id: " + id));
        Contact newct = ContactMapper.DtoToEntity(inct, ctowner.get());
        newct.setId(ct.getId());
        contactService.save(newct);
        return ResponseEntity.ok().body(newct);
    }

    // Delete contacts by ID DELETE /api/contacts/{id}
    @DeleteMapping(value = "/contacts/{id}")
    ResponseEntity deleteContact(@PathVariable("id") int id) {
        Contact ct = contactService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No contact  with id: " + id));

        contactService.delete(ct.getId());
        return ResponseEntity.ok().body("Contact with ID '" + id + "' was successfully deleted");
    }
}
