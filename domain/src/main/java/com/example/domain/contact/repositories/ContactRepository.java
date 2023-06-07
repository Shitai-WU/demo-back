package com.example.domain.contact.repositories;


import com.example.domain.contact.models.Contact;

import java.util.List;

public interface ContactRepository {
    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(String id);

    Contact findById(String id);

    List<Contact> findAll();
}
