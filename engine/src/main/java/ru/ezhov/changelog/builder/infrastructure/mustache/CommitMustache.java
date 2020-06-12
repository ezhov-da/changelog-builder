package ru.ezhov.changelog.builder.infrastructure.mustache;

import ru.ezhov.changelog.builder.domain.Commit;
import ru.ezhov.changelog.builder.domain.CommitDateTimeFormat;

import java.time.format.DateTimeFormatter;

public class CommitMustache {
    private final String type;
    private final String scope;
    private final String description;
    private final String commitUsername;
    private final String commitDateTime;

    public CommitMustache(Commit commit, CommitDateTimeFormat commitDateTimeFormat) {
        this.type = commit.type().value();
        this.scope = commit.scope().value().orElse(null);
        this.description = commit.description().value();
        this.commitUsername = commit.commitUsername().value();
        this.commitDateTime = commit.commitDateTime().value().format(DateTimeFormatter.ofPattern(commitDateTimeFormat.value()));
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

    public String getCommitDateTime() {
        return commitDateTime;
    }

    public boolean hasScope() {
        return scope != null && !"".equals(scope);
    }
}
