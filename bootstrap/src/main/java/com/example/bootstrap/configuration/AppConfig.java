package com.example.bootstrap.configuration;

import com.example.application.contact.CreateContact;
import com.example.application.contact.DeleteContact;
import com.example.application.contact.FindContact;
import com.example.domain.contact.repositories.ContactRepository;
import com.example.infrastructure.persistence.contact.daos.ContactDAO;
import com.example.infrastructure.persistence.contact.repositories.HibernateContactRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CreateContact createContact(ContactRepository contactRepository) {
        return new CreateContact(contactRepository);
    }

    @Bean
    public ContactRepository contactRepository(ContactDAO contactDAO) {
        return new HibernateContactRepository(contactDAO);
    }

    @Bean
    public DeleteContact deleteContact(ContactRepository contactRepository) {
        return new DeleteContact(contactRepository);
    }

    @Bean
    public FindContact findContact(ContactRepository contactRepository) {
        return new FindContact(contactRepository);
    }
}
