package com.givaudan.demo.contact.application;

import com.givaudan.demo.contact.domain.model.Contact;
import com.givaudan.demo.contact.domain.repository.ContactRepository;

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
