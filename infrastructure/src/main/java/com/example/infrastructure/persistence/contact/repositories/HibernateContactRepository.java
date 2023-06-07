package com.example.infrastructure.persistence.contact.repositories;

import com.example.domain.contact.models.Address;
import com.example.domain.contact.models.Contact;
import com.example.domain.contact.repositories.ContactRepository;
import com.example.infrastructure.persistence.contact.daos.ContactDAO;
import com.example.infrastructure.persistence.contact.entities.ContactEntity;
import com.example.infrastructure.persistence.contact.mappers.ContactEntityMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class HibernateContactRepository implements ContactRepository {
    private final ContactDAO contactDAO;

    private final ContactEntityMapper mapper = Mappers.getMapper(ContactEntityMapper.class);

    public HibernateContactRepository(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity contactEntity = contactDAO.save(mapper.contactToContactEntity(contact));
        return mapper.contactEntityToContact(contactEntity);
    }

    @Override
    @Transactional
    public Contact update(Contact contact) {
        ContactEntity contactEntity = contactDAO.findById(contact.getId()).orElseThrow();

        contactEntity.setFirstName(contact.getFirstName());
        contactEntity.setLastName(contact.getLastName());
        contactEntity.setEmail(contact.getEmail());
        contactEntity.setTelephone(contact.getTelephone());
        contactEntity.setBirthday(contact.getBirthday());
        Address address = contact.getAddress();
        contactEntity.setAddress(
                ContactEntity.Address.builder()
                        .address1(address.address1())
                        .address2(address.address2())
                        .address3(address.address3())
                        .build());

        return mapper.contactEntityToContact(contactEntity);
    }

    @Override
    public void deleteById(String id) {
        contactDAO.deleteById(id);
    }

    @Override
    public Contact findById(String id) {
        Optional<ContactEntity> contactEntity = contactDAO.findById(id);
        return mapper.contactEntityToContact(contactEntity.orElseThrow());
    }

    @Override
    public List<Contact> findAll() {
        return mapper.contactEntitiesToContacts(contactDAO.findAll());
    }
}
