package com.givaudan.demo.contact.infrastructure.persistence.mappers;

import com.givaudan.demo.contact.domain.models.Contact;
import com.givaudan.demo.contact.infrastructure.persistence.entities.ContactEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ContactEntityMapper {
    ContactEntity contactToContactEntity(Contact contact);

    Contact contactEntityToContact(ContactEntity contactEntity);

    List<Contact> contactEntitiesToContacts(Collection<ContactEntity> contactEntities);
}
