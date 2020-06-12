package ru.ezhov.changelog.builder.domain;

public class CommitRepositoryException extends Exception {
    public CommitRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
