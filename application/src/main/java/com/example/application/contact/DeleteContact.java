package com.example.application.contact;


import com.example.domain.contact.repositories.ContactRepository;

public class DeleteContact {
    private final ContactRepository contactRepository;

    public DeleteContact(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void deleteById(String id) {
        contactRepository.deleteById(id);
    }
}
