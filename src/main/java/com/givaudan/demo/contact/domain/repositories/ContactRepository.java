package com.givaudan.demo.contact.domain.repositories;

import com.givaudan.demo.contact.domain.models.Contact;

import java.util.List;

public interface ContactRepository {
    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(String id);

    Contact findById(String id);

    List<Contact> findAll();
}
