package com.givaudan.demo.contact.domain.models;

import static java.util.Objects.requireNonNull;

public record Address(String address1, String address2, String address3) {
    public Address {
        requireNonNull(address1);
    }
}
