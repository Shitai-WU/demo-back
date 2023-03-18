package com.givaudan.demo.contact.infrastructure.persistence.dao;

import com.givaudan.demo.contact.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDAO extends JpaRepository<ContactEntity, String> {
}
