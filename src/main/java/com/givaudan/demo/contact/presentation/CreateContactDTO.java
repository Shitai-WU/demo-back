package com.givaudan.demo.contact.presentation;

import lombok.Builder;

@Builder
public record CreateContactDTO(String firstName, String lastName, java.util.Date birthday, String telephone,
                               String email,
                               String address1, String address2, String address3) {
}
