package com.givaudan.demo.contact.infrastructure.persistence.repositories;

import com.givaudan.demo.contact.domain.models.Address;
import com.givaudan.demo.contact.domain.models.Contact;
import com.givaudan.demo.contact.infrastructure.persistence.daos.ContactDAO;
import com.givaudan.demo.contact.infrastructure.persistence.entities.ContactEntity;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class HibernateContactRepositoryTest {
    @InjectMocks
    private HibernateContactRepository hibernateContactRepository;
    @Mock
    private ContactDAO contactDAO;

    @Test
    void should_save_contact() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example@email.com",
                new Address("address 1", null, null));

        hibernateContactRepository.save(contact);

        verify(contactDAO).save(any(ContactEntity.class));
    }

    @Test
    void should_update_contact() {
        Contact contact = Contact.create("new firstName", "new lastName", LocalDate.of(2000, 1, 1), "087654321", "example@email.com",
                new Address("address 1 bis", null, null));

        ContactEntity contactEntity = ContactEntity
                .builder()
                .id(contact.getId())
                .firstName("firstName")
                .lastName("lastName")
                .birthday(LocalDate.of(2000, 1, 1))
                .telephone("01234567")
                .email("example@email.com")
                .address(ContactEntity.Address
                        .builder()
                        .address1("address 1")
                        .build())
                .build();
        when(contactDAO.findById(contact.getId())).thenReturn(Optional.of(contactEntity));

        Contact updatedContact = hibernateContactRepository.update(contact);

        verify(contactDAO).findById(contact.getId());
        assertAll(
                "Grouped assertion of update contact",
                () -> assertEquals(contact.getId(), updatedContact.getId()),
                () -> assertEquals("new firstName", updatedContact.getFirstName()),
                () -> assertEquals("new lastName", updatedContact.getLastName()),
                () -> assertEquals(LocalDate.of(2000, 1, 1), updatedContact.getBirthday()),
                () -> assertEquals("087654321", updatedContact.getTelephone()),
                () -> assertEquals("example@email.com", updatedContact.getEmail()),
                () -> assertEquals(new Address("address 1 bis", null, null), updatedContact.getAddress())
        );
    }

    @Test
    public void should_throw_exception_when_contact_to_update_not_found() {
        Contact contact = Contact.create("new firstName", "new lastName", LocalDate.of(2000, 1, 1), "087654321", "example@email.com",
                new Address("address 1 bis", null, null));
        when(contactDAO.findById(contact.getId())).thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> hibernateContactRepository.update(contact)
        );
    }

    @Test
    public void should_delete_contact_by_id() {
        hibernateContactRepository.deleteById("id");

        verify(contactDAO).deleteById("id");
    }

    @Test
    public void should_find_contact_by_id() {
        ContactEntity contactEntity = ContactEntity
                .builder()
                .id("id")
                .firstName("firstName")
                .lastName("lastName")
                .birthday(LocalDate.of(2000, 1, 1))
                .telephone("01234567")
                .email("example@email.com")
                .address(ContactEntity.Address
                        .builder()
                        .address1("address 1")
                        .build())
                .build();
        when(contactDAO.findById("id")).thenReturn(Optional.of(contactEntity));

        hibernateContactRepository.findById("id");

        verify(contactDAO).findById("id");
    }

    @Test
    public void should_throw_exception_when_contact_not_found() {
        when(contactDAO.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> hibernateContactRepository.findById("id")
        );
    }

    @Test
    public void should_find_all_contacts() {
        hibernateContactRepository.findAll();

        verify(contactDAO).findAll();
    }
}