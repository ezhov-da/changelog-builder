package ru.ezhov.changelog.builder.infrastructure.mustache;

import ru.ezhov.changelog.builder.domain.Log;

public class LogMustache {
    private final String type;
    private final String scope;
    private final String description;
    private final String commitUsername;
    private final String commitDate;

    public LogMustache(Log log) {
        this.type = log.type().value();
        this.scope = log.scope().value().orElse(null);
        this.description = log.description().value();
        this.commitUsername = log.commitUsername().value();
        this.commitDate = log.commitDateTime().value().toString();
    }

    public String getType() {
        return type;
    }

    public String getScope() {
        return scope;
    }

    public String getDescription() {
        return description;
    }

    public String getCommitUsername() {
        return commitUsername;
    }

    public String getCommitDate() {
        return commitDate;
    }
}
