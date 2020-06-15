package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.ChangelogFileDirectory;
import ru.ezhov.changelog.builder.engine.domain.ChangelogFilename;
import ru.ezhov.changelog.builder.engine.domain.CommitDateFormat;
import ru.ezhov.changelog.builder.engine.domain.CommitDateTimeFormat;
import ru.ezhov.changelog.builder.engine.domain.Configuration;
import ru.ezhov.changelog.builder.engine.domain.Vcs;

public class DefaultConfiguration implements Configuration {
    public static final String DEFAULT_VCS = "GIT";
    public static final String CHANGELOG_FILE_DIRECTORY = ".";
    public static final String CHANGELOG_FILENAME = "CHANGELOG.md";
    public static final String COMMIT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String COMMIT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    DefaultConfiguration() {
    }

    @Override
    public Vcs vcs() {
        return Vcs.valueOf("GIT");
    }

    @Override
    public ChangelogFileDirectory changelogFileDirectory() {
        return ChangelogFileDirectory.create(CHANGELOG_FILE_DIRECTORY);
    }

    @Override
    public ChangelogFilename changelogFilename() {
        return ChangelogFilename.create(CHANGELOG_FILENAME);
    }

    @Override
    public CommitDateFormat commitDateFormat() {
        return CommitDateFormat.create(COMMIT_DATE_FORMAT);
    }

    @Override
    public CommitDateTimeFormat commitDateTimeFormat() {
        return CommitDateTimeFormat.create(COMMIT_DATE_TIME_FORMAT);
    }
}
