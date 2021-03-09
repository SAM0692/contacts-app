package com.samdev.contactapp.mapper;

import com.samdev.contactapp.dto.ContactDTO;
import com.samdev.contactapp.model.Contact;
import com.samdev.contactapp.model.ContactOwner;

public class ContactMapper {

    public static Contact DtoToEntity(ContactDTO ct, ContactOwner ctowner) {
        return new Contact(ct.getName(), ct.getLastName(), ct.getPhoneNumber(), ctowner);
    }

    public static ContactDTO EntityToDto(Contact ct) {
        return new ContactDTO(ct.getName(), ct.getLastName(), ct.getPhoneNumber(), ct.getOwner().getId());
    }
}
