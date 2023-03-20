package com.givaudan.demo.contact.infrastructure.persistence.daos;

import com.givaudan.demo.contact.infrastructure.persistence.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDAO extends JpaRepository<ContactEntity, String> {
}
