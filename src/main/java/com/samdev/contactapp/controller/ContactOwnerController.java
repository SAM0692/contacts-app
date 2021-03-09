package com.samdev.contactapp.controller;

import java.net.URI;
import java.util.List;

import com.samdev.contactapp.dto.ContactOwnerDTO;
import com.samdev.contactapp.exception.ResourceNotFoundException;
import com.samdev.contactapp.mapper.ContactOwnerMapper;
import com.samdev.contactapp.model.ContactOwner;
import com.samdev.contactapp.service.ContactOwnerService;

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
public class ContactOwnerController {
    @Autowired
    private ContactOwnerService contactOwnerService;

    // Get all contact owners GET /api/owners
    @GetMapping(value = "/owners")
    List<ContactOwner> getAll() {
        return contactOwnerService.getAllContactOwners();
    }

    // Get single contact owners by ID GET /api/owners/{id}
    @GetMapping(value = "/owners/{id}")
    ResponseEntity<ContactOwner> getById(@PathVariable("id") int id) {
        ContactOwner ctowner = contactOwnerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No contact owner with id: " + id));

        return ResponseEntity.ok().body(ctowner);
    }

    // Create new contact owners POST /api/owners
    @PostMapping(value = "/owners")
    ResponseEntity<?> createContactOwner(@RequestBody ContactOwnerDTO inctowner) {
        ContactOwner ctowner = ContactOwnerMapper.DtoToEntity(inctowner);
        ContactOwner addedctowner = contactOwnerService.save(ctowner);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedctowner.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // Update contact owners details PUT /api/owners/{id}
    @PutMapping(value = "/owners/{id}")
    ResponseEntity<ContactOwner> updateContactOwner(@PathVariable("id") int id,
            @RequestBody ContactOwnerDTO inctowner) {
        ContactOwner ctowner = contactOwnerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No contact owner with id: " + id));
        ContactOwner newctowner = ContactOwnerMapper.DtoToEntity(inctowner);
        newctowner.setId(ctowner.getId());
        contactOwnerService.save(newctowner);
        return ResponseEntity.ok().body(newctowner);
    }

    // Delete contact owners by ID DELETE /api/owners/{id}
    @DeleteMapping(value = "/owners/{id}")
    ResponseEntity deleteContactOwner(@PathVariable("id") int id) {
        ContactOwner ctowner = contactOwnerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No contact owner with id: " + id));

        contactOwnerService.delete(ctowner.getId());
        return ResponseEntity.ok().body("Contact Owner with ID '" + id + "' was successfully deleted");
    }
}
