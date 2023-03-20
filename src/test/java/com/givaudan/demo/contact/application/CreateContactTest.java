package com.givaudan.demo.contact.application;

import com.givaudan.demo.contact.domain.models.Address;
import com.givaudan.demo.contact.domain.models.Contact;
import com.givaudan.demo.contact.domain.repositories.ContactRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateContactTest {
    @Mock
    private ContactRepository contactRepository;
    @InjectMocks
    private CreateContact createContact;

    @Test
    void should_create_contact() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example.email.com",
                new Address("address 1", null, null));

        createContact.create(contact);

        verify(contactRepository).save(contact);
    }

    @Test
    void should_update_contact() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example.email.com",
                new Address("address 1", null, null));

        createContact.update(contact);

        verify(contactRepository).update(contact);
    }
}