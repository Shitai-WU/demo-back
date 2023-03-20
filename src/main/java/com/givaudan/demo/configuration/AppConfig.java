package com.givaudan.demo.configuration;

import com.givaudan.demo.contact.application.CreateContact;
import com.givaudan.demo.contact.application.DeleteContact;
import com.givaudan.demo.contact.application.FindContact;
import com.givaudan.demo.contact.domain.repositories.ContactRepository;
import com.givaudan.demo.contact.infrastructure.persistence.daos.ContactDAO;
import com.givaudan.demo.contact.infrastructure.persistence.repositories.HibernateContactRepository;
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
