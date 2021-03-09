package com.samdev.contactapp.repository;

import java.util.List;

import com.samdev.contactapp.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public List<Contact> findByOwnerId(int id);
}
