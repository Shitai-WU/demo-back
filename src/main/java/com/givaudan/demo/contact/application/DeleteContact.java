package com.givaudan.demo.contact.application;

import com.givaudan.demo.contact.domain.repositories.ContactRepository;

public class DeleteContact {
    private final ContactRepository contactRepository;

    public DeleteContact(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void deleteById(String id) {
        contactRepository.deleteById(id);
    }
}
