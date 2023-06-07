package com.example.bootstrap;

import com.example.application.contact.CreateContact;
import com.example.domain.contact.models.Address;
import com.example.domain.contact.models.Contact;
import com.example.infrastructure.presentation.contact.ContactDTO;
import com.example.infrastructure.presentation.contact.CreateContactDTO;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ContactControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CreateContact createContact;

    @Test
    public void should_create_contact() {
        CreateContactDTO createContactDTO = CreateContactDTO
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthday(new Date())
                .email("example@email.com")
                .telephone("01234567")
                .address1("address 1")
                .build();
        ResponseEntity<ContactDTO> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/contacts", createContactDTO, ContactDTO.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void should_update_contact() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example@email.com",
                new Address("address 1", null, null));
        createContact.create(contact);

        ContactDTO contactDTO = ContactDTO
                .builder()
                .id(contact.getId())
                .firstName("new firstName")
                .lastName("new lastName")
                .birthday(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant()))
                .email("example@email.com")
                .telephone("07654321")
                .address1("address 1 bis")
                .build();
        ResponseEntity<ContactDTO> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/contacts", HttpMethod.PUT, new HttpEntity<>(contactDTO), ContactDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void should_return_error_code_for_update_when_contact_not_found() {
        ContactDTO contactDTO = ContactDTO
                .builder()
                .id("id")
                .firstName("new firstName")
                .lastName("new lastName")
                .birthday(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant()))
                .email("example@email.com")
                .telephone("07654321")
                .address1("address 1 bis")
                .build();
        ResponseEntity<ContactDTO> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/contacts", HttpMethod.PUT, new HttpEntity<>(contactDTO), ContactDTO.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void should_find_contact_by_id() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example@email.com",
                new Address("address 1", null, null));
        createContact.create(contact);

        ResponseEntity<ContactDTO> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/contacts/" + contact.getId(), ContactDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void should_return_not_found_code_when_contact_not_found() {
        ResponseEntity<ContactDTO> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/contacts/id", ContactDTO.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void should_return_all_contacts() {
        ResponseEntity<List<ContactDTO>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/contacts", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void should_delete_contact_by_id() {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example@email.com",
                new Address("address 1", null, null));
        createContact.create(contact);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/contacts/" + contact.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}