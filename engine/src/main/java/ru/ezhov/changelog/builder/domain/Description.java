package ru.ezhov.changelog.builder.domain;

public class Description {
    private final String value;

    private Description(String value) {
        this.value = value;
    }

    public static Description create(String value) {
        return new Description(value != null ? value.trim() : null);
    }

    public String value() {
        return value;
    }
}
