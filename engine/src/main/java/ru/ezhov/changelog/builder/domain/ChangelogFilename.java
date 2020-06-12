package ru.ezhov.changelog.builder.domain;

public class ChangelogFilename {
    private final String value;

    private ChangelogFilename(String value) {
        this.value = value;
    }

    public static ChangelogFilename create(String value) {
        return new ChangelogFilename(value);
    }

    public String value() {
        return value;
    }
}
