package com.example.infrastructure.persistence.contact.mappers;

import com.example.domain.contact.models.Contact;
import com.example.infrastructure.persistence.contact.entities.ContactEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ContactEntityMapper {
    ContactEntity contactToContactEntity(Contact contact);

    Contact contactEntityToContact(ContactEntity contactEntity);

    List<Contact> contactEntitiesToContacts(Collection<ContactEntity> contactEntities);
}
