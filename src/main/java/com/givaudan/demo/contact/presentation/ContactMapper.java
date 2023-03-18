package com.givaudan.demo.contact.presentation;

import com.givaudan.demo.contact.domain.model.Address;
import com.givaudan.demo.contact.domain.model.Contact;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper
public abstract class ContactMapper {

    ContactDTO contactToContactDTO(Contact contact) {
        return ContactDTO
                .builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .telephone(contact.getTelephone())
                .birthday(Date.from(contact.getBirthday().atStartOfDay(ZoneOffset.UTC).toInstant()))
                .email(contact.getEmail())
                .address1(contact.getAddress().address1())
                .address2(contact.getAddress().address2())
                .address3(contact.getAddress().address3())
                .build();
    }

    Contact CreateContactDTOToContact(CreateContactDTO contactDTO) {
        return Contact.create(
                contactDTO.firstName(),
                contactDTO.lastName(),
                LocalDateTime.ofInstant(contactDTO.birthday().toInstant(), ZoneOffset.UTC).toLocalDate(),
                contactDTO.telephone(),
                contactDTO.email(),
                new Address(contactDTO.address1(), contactDTO.address2(), contactDTO.address3()));
    }

    abstract List<ContactDTO> contactsToContactDTOs(Collection<Contact> contacts);

    Contact contactDTOToContact(ContactDTO contactDTO) {
        return Contact.builder()
                .id(contactDTO.id())
                .firstName(contactDTO.firstName())
                .lastName(contactDTO.lastName())
                .email(contactDTO.email())
                .birthday(LocalDateTime.ofInstant(contactDTO.birthday().toInstant(), ZoneOffset.UTC).toLocalDate())
                .telephone(contactDTO.telephone())
                .address(new Address(contactDTO.address1(), contactDTO.address2(), contactDTO.address3()))
                .build();
    }
}
