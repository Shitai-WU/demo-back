package com.example.application.contact;


import com.example.domain.contact.models.Contact;
import com.example.domain.contact.repositories.ContactRepository;

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
