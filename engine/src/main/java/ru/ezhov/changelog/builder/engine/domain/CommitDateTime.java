package ru.ezhov.changelog.builder.engine.domain;

import java.time.LocalDateTime;

public class CommitDateTime {
    private final LocalDateTime value;

    private CommitDateTime(LocalDateTime value) {
        this.value = value;
    }

    public static CommitDateTime create(LocalDateTime value) {
        return new CommitDateTime(value);
    }

    public LocalDateTime value() {
        return value;
    }

}
