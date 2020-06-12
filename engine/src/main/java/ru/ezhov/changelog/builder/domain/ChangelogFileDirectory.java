package ru.ezhov.changelog.builder.domain;

public class ChangelogFileDirectory {
    private final String value;

    private ChangelogFileDirectory(String value) {
        this.value = value;
    }

    public static ChangelogFileDirectory create(String value) {
        return new ChangelogFileDirectory(value);
    }

    public String value() {
        return value;
    }
}
