package com.example.application.contact;

import com.example.domain.contact.repositories.ContactRepository;
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
class FindContactTest {
    @Mock
    private ContactRepository contactRepository;
    @InjectMocks
    private FindContact findContact;

    @Test
    void find_contact_by_id() {
        findContact.findById("id");

        verify(contactRepository).findById("id");
    }

    @Test
    void find_all_contacts() {
        findContact.findAll();

        verify(contactRepository).findAll();
    }
}