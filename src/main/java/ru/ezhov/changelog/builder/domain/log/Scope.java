package ru.ezhov.changelog.builder.domain.log;

import java.util.Optional;

public class Scope {
    private final String value;

    private Scope(String value) {
        this.value = value;
    }

    public Scope create(String value) {
        return new Scope(value);
    }

    public Optional<String> value() {
        return Optional.ofNullable(value);
    }
}
