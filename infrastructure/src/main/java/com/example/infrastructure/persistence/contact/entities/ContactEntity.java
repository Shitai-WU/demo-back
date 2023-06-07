package com.example.infrastructure.persistence.contact.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "CONTACT")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String telephone;
    private String email;
    @Embedded
    private Address address;

    @Embeddable
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Address {
        private String address1;
        private String address2;
        private String address3;
    }
}
