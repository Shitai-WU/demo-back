package com.givaudan.demo.contact.application;

import com.givaudan.demo.contact.domain.repositories.ContactRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeleteContactTest {
    @Mock
    private ContactRepository contactRepository;
    @InjectMocks
    private DeleteContact deleteContact;

    @Test
    void should_delete_contact_by_id() {
        deleteContact.deleteById("id");

        verify(contactRepository).deleteById("id");
    }
}