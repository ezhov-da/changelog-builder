package ru.ezhov.changelog.builder.domain;

public class CommitUsername {
    private final String value;

    private CommitUsername(String value) {
        this.value = value;
    }

    public static CommitUsername create(String value) {
        return new CommitUsername(value);
    }

    public String value() {
        return value;
    }
}
