package com.givaudan.demo.contact.application;

import com.givaudan.demo.contact.domain.models.Contact;
import com.givaudan.demo.contact.domain.repositories.ContactRepository;

public class CreateContact {
    private final ContactRepository contactRepository;

    public CreateContact(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact update(Contact contact) {
        return contactRepository.update(contact);
    }
}
