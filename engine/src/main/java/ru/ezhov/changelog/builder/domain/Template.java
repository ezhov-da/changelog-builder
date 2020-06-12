package ru.ezhov.changelog.builder.domain;

public class Template {
    private final String value;

    private Template(String value) {
        this.value = value;
    }

    public static Template create(String value) {
        return new Template(value);
    }

    public String value() {
        return value;
    }
}
