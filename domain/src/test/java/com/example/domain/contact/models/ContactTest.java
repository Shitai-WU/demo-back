package com.example.domain.contact.models;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ContactTest {

    @Test
    void should_create_contact() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example.email.com",
                new Address("address 1", null, null));

        assertAll(
                "Grouped assertion of create contact",
                () -> assertNotNull(contact.getId()),
                () -> assertEquals("firstName", contact.getFirstName()),
                () -> assertEquals("lastName", contact.getLastName()),
                () -> assertEquals(LocalDate.of(2000, 1, 1), contact.getBirthday()),
                () -> assertEquals("01234567", contact.getTelephone()),
                () -> assertEquals("example.email.com", contact.getEmail()),
                () -> assertEquals(new Address("address 1", null, null), contact.getAddress())
        );
    }

    @Test
    void should_throw_exception_when_fist_name_is_null() {
        assertThrows(
                NullPointerException.class,
                () -> Contact.create(null, "lastName", LocalDate.of(2000, 1, 1), "01234567", "example.email.com",
                        new Address("address 1", null, null))
        );
    }
}