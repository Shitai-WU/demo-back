package com.givaudan.demo.contact.domain.repository;

import com.givaudan.demo.contact.domain.model.Contact;

import java.util.List;

public interface ContactRepository {
    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(String id);

    Contact findById(String id);

    List<Contact> findAll();
}
