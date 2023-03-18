package com.givaudan.demo.configuration;

import com.givaudan.demo.contact.application.CreateContact;
import com.givaudan.demo.contact.application.DeleteContact;
import com.givaudan.demo.contact.application.FindContact;
import com.givaudan.demo.contact.domain.repository.ContactRepository;
import com.givaudan.demo.contact.infrastructure.persistence.dao.ContactDAO;
import com.givaudan.demo.contact.infrastructure.persistence.repository.HibernateContactRepository;
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
