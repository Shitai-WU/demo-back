package com.givaudan.demo.contact.application;

import com.givaudan.demo.contact.domain.models.Contact;
import com.givaudan.demo.contact.domain.repositories.ContactRepository;

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
