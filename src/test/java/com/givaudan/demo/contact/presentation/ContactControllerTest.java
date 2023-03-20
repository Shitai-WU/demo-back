package com.givaudan.demo.contact.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.givaudan.demo.contact.application.CreateContact;
import com.givaudan.demo.contact.application.DeleteContact;
import com.givaudan.demo.contact.application.FindContact;
import com.givaudan.demo.contact.domain.models.Address;
import com.givaudan.demo.contact.domain.models.Contact;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(ContactController.class)
class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateContact createContact;
    @MockBean
    private FindContact findContact;
    @MockBean
    private DeleteContact deleteContact;

    @Test
    void should_create_contact() throws Exception {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example.email.com",
                new Address("address 1", null, null));
        when(createContact.create(any(Contact.class))).thenReturn(contact);

        ObjectMapper objectMapper = new ObjectMapper();
        CreateContactDTO createContactDTO = CreateContactDTO
                .builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthday(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant()))
                .email("example@email.com")
                .telephone("01234567")
                .address1("address 1")
                .build();
        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createContactDTO)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void should_update_contact() throws Exception {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example.email.com",
                new Address("address 1", null, null));
        when(createContact.update(any(Contact.class))).thenReturn(contact);

        ObjectMapper objectMapper = new ObjectMapper();
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
        mockMvc.perform(put("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDTO)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void should_find_contact_by_id() throws Exception {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example@email.com",
                new Address("address 1", null, null));
        when(findContact.findById(anyString())).thenReturn(contact);

        mockMvc.perform(get("/api/contacts/" + contact.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void should_find_all_contacts() throws Exception {
        Contact contact = Contact.create("firstName", "lastName", LocalDate.of(2000, 1, 1), "01234567", "example@email.com",
                new Address("address 1", null, null));
        when(findContact.findAll()).thenReturn(Collections.singletonList(contact));

        mockMvc.perform(get("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void should_delete_contact_by_id() throws Exception {
        mockMvc.perform(delete("/api/contacts/id").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()));
    }
}