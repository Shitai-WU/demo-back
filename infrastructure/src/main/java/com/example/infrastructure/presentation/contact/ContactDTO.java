package com.example.infrastructure.presentation.contact;

import lombok.Builder;

import java.util.Date;

@Builder
public record ContactDTO(String id, String firstName, String lastName, Date birthday, String telephone,
                         String email, String address1, String address2, String address3) {
}
