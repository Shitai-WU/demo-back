package com.givaudan.demo.contact.presentation;

import com.givaudan.demo.contact.application.CreateContact;
import com.givaudan.demo.contact.application.DeleteContact;
import com.givaudan.demo.contact.application.FindContact;
import com.givaudan.demo.contact.domain.model.Contact;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final CreateContact createContact;

    private final FindContact findContact;

    private final DeleteContact deleteContact;

    private final ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    public ContactController(CreateContact createContact, FindContact findContact, DeleteContact deleteContact) {
        this.createContact = createContact;
        this.findContact = findContact;
        this.deleteContact = deleteContact;
    }

    @PostMapping
    public ResponseEntity<ContactDTO> create(@RequestBody CreateContactDTO createContactDTO) {
        Contact contact = createContact.create(contactMapper.CreateContactDTOToContact(createContactDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(contactMapper.contactToContactDTO(contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> find(@PathVariable String id) {
        Contact contact = findContact.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(contactMapper.contactToContactDTO(contact));
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> findAll() {
        List<Contact> contacts = findContact.findAll();
        return ResponseEntity.status((HttpStatus.FOUND)).body(contactMapper.contactsToContactDTOs(contacts));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteContact.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<ContactDTO> update(@RequestBody ContactDTO contactDTO) {
        Contact contact = createContact.update(contactMapper.contactDTOToContact(contactDTO));
        return ResponseEntity.status(HttpStatus.OK).body(contactMapper.contactToContactDTO(contact));
    }
}
