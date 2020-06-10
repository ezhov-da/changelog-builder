package ru.ezhov.changelog.builder.infrastructure.mustache;

import java.time.LocalDate;
import java.util.List;

public class CommitDayMustache {
    private final LocalDate commitDate;
    private final List<LogMustache> logs;

    public CommitDayMustache(LocalDate commitDate, List<LogMustache> logs) {
        this.commitDate = commitDate;
        this.logs = logs;
    }

    public LocalDate getCommitDate() {
        return commitDate;
    }

    public List<LogMustache> getLogs() {
        return logs;
    }
}
