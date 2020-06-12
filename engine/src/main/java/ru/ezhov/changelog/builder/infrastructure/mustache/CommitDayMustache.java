package ru.ezhov.changelog.builder.infrastructure.mustache;

import ru.ezhov.changelog.builder.domain.CommitDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommitDayMustache {
    private final String commitDate;
    private final List<CommitMustache> commits;

    public CommitDayMustache(LocalDate commitDate, CommitDateFormat commitDateFormat, List<CommitMustache> commits) {
        this.commitDate = commitDate.format(DateTimeFormatter.ofPattern(commitDateFormat.value()));
        this.commits = commits;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public List<CommitMustache> getCommits() {
        return commits;
    }
}
