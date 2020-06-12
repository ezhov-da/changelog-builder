package ru.ezhov.changelog.builder.domain;

import java.util.List;

public interface ChangelogViewer {
    String create(
            Template template,
            CommitDateFormat commitDateFormat,
            CommitDateTimeFormat commitDateTimeFormat,
            List<Commit> commits
    ) throws ChangelogViewerException;
}
