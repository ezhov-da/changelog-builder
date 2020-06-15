package ru.ezhov.changelog.builder.engine.domain;

public class CommitDateFormat {
    private final String value;

    private CommitDateFormat(String value) {
        this.value = value;
    }

    public static CommitDateFormat create(String value) {
        return new CommitDateFormat(value);
    }

    public String value() {
        return value;
    }
}
