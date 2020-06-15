package ru.ezhov.changelog.builder.engine.domain;

public interface Configuration {
    Vcs vcs();

    ChangelogFileDirectory changelogFileDirectory();

    ChangelogFilename changelogFilename();

    CommitDateFormat commitDateFormat();

    CommitDateTimeFormat commitDateTimeFormat();
}
