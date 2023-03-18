package com.givaudan.demo.contact.domain.model;

import com.givaudan.demo.shared.AggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
@SuperBuilder
public class Contact extends AggregateRoot<String> {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String telephone;
    private String email;

    private Address address;

    private Contact(String id, String firstName, String lastName, LocalDate birthday, String telephone, String email, Address address) {
        super(id);
        requireNonNull(firstName);
        requireNonNull(lastName);
        requireNonNull(birthday);
        requireNonNull(telephone);
        requireNonNull(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public static Contact create(String firstName, String lastName, LocalDate birthday, String telephone, String email, Address address) {
        return new Contact(UUID.randomUUID().toString(), firstName, lastName, birthday, telephone, email, address);
    }
}
