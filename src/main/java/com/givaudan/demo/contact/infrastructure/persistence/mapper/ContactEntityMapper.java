package com.givaudan.demo.contact.infrastructure.persistence.mapper;

import com.givaudan.demo.contact.domain.model.Contact;
import com.givaudan.demo.contact.infrastructure.persistence.entity.ContactEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ContactEntityMapper {
    ContactEntity contactToContactEntity(Contact contact);

    Contact contactEntityToContact(ContactEntity contactEntity);

    List<Contact> contactEntitiesToContacts(Collection<ContactEntity> contactEntities);
}
