package ru.ezhov.changelog.builder.infrastructure.mustache;

import java.util.List;

public class CommitsMustache {
    private List<CommitDayMustache> commitDays;

    public CommitsMustache(List<CommitDayMustache> commitDays) {
        this.commitDays = commitDays;
    }

    public List<CommitDayMustache> getCommitDays() {
        return commitDays;
    }
}
