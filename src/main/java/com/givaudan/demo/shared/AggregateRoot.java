package com.givaudan.demo.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import static java.util.Objects.requireNonNull;

@Getter
@EqualsAndHashCode
@SuperBuilder
public class AggregateRoot<Id> {
    private Id id;

    public AggregateRoot(Id id) {
        requireNonNull(id);
        this.id = id;
    }
}