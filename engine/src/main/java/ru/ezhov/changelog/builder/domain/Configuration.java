package ru.ezhov.changelog.builder.domain;

public interface Configuration {
    Vcs vcs();

    ChangelogFileDirectory changelogFileDirectory();

    ChangelogFilename changelogFilename();

    CommitDateFormat commitDateFormat();

    CommitDateTimeFormat commitDateTimeFormat();
}
