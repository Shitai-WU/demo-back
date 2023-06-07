package com.example.application.contact;


import com.example.domain.contact.models.Contact;
import com.example.domain.contact.repositories.ContactRepository;

import java.util.List;

public class FindContact {
    private final ContactRepository contactRepository;

    public FindContact(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact findById(String id) {
        return contactRepository.findById(id);
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
}
