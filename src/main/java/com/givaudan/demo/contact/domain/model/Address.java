package com.givaudan.demo.contact.domain.model;

import static java.util.Objects.requireNonNull;

public record Address(String address1, String address2, String address3) {
    public Address {
        requireNonNull(address1);
    }
}
