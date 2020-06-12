package ru.ezhov.changelog.builder.domain;

public class CommitDateTimeFormat {
    private final String value;

    private CommitDateTimeFormat(String value) {
        this.value = value;
    }

    public static CommitDateTimeFormat create(String value) {
        return new CommitDateTimeFormat(value);
    }

    public String value() {
        return value;
    }
}
