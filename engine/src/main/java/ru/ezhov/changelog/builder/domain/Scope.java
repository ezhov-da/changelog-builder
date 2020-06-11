package ru.ezhov.changelog.builder.domain;

import java.util.Optional;

public class Scope {
    private final String value;

    private Scope(String value) {
        this.value = value;
    }

    public static Scope create(String value) {
        return new Scope(value);
    }

    public static Scope empty() {
        return new Scope(null);
    }

    public Optional<String> value() {
        return Optional.ofNullable(value);
    }
}
