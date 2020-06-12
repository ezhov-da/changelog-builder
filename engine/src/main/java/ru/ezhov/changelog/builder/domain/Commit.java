package ru.ezhov.changelog.builder.domain;

import java.time.LocalDate;

public class Commit {
    private final Type type;
    private final Scope scope;
    private final Description description;
    private final CommitUsername commitUsername;
    private final CommitDateTime commitDateTime;

    private Commit(Type type, Scope scope, Description description, CommitUsername commitUsername, CommitDateTime commitDateTime) {
        this.type = type;
        this.scope = scope;
        this.description = description;
        this.commitUsername = commitUsername;
        this.commitDateTime = commitDateTime;
    }

    public static Commit create(Type type, Scope scope, Description description, CommitUsername commitUsername, CommitDateTime commitDateTime) {
        return new Commit(type, scope, description, commitUsername, commitDateTime);
    }

    public Type type() {
        return type;
    }

    public Scope scope() {
        return scope;
    }

    public Description description() {
        return description;
    }

    public CommitUsername commitUsername() {
        return commitUsername;
    }

    public CommitDateTime commitDateTime() {
        return commitDateTime;
    }

    public LocalDate commitDate() {
        return commitDateTime.value().toLocalDate();
    }
}
