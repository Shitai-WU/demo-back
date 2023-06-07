package com.example.infrastructure.persistence.contact.daos;

import com.example.infrastructure.persistence.contact.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDAO extends JpaRepository<ContactEntity, String> {
}
