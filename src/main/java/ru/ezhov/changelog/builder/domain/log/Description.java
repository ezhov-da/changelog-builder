package ru.ezhov.changelog.builder.domain.log;

import java.util.Optional;

public class Description {
    private final String value;

    private Description(String value) {
        this.value = value;
    }

    public Description create(String value) {
        return new Description(value);
    }

    public Optional<String> value() {
        return Optional.ofNullable(value);
    }
}
