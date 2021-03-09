package com.samdev.contactapp.repository;

import com.samdev.contactapp.model.ContactOwner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactOwnerRepository extends JpaRepository<ContactOwner, Integer> {

}
