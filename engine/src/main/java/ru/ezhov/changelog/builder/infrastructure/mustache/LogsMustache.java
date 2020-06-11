package ru.ezhov.changelog.builder.infrastructure.mustache;

import java.util.List;

public class LogsMustache {
    private List<CommitDayMustache> commitDays;

    public LogsMustache(List<CommitDayMustache> commitDays) {
        this.commitDays = commitDays;
    }

    public List<CommitDayMustache> getCommitDays() {
        return commitDays;
    }
}
