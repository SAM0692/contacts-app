package com.samdev.contactapp.mapper;

import com.samdev.contactapp.dto.ContactOwnerDTO;
import com.samdev.contactapp.model.ContactOwner;

public class ContactOwnerMapper {

    public static ContactOwner DtoToEntity(ContactOwnerDTO ctowner) {
        return new ContactOwner(ctowner.getName(), ctowner.getLastName());
    }

    public static ContactOwnerDTO EntityToDto(ContactOwner ctowner) {
        return new ContactOwnerDTO(ctowner.getName(), ctowner.getLastName());
    }
}
