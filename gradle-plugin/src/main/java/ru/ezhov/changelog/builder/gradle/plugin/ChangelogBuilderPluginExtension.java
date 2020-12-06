package ru.ezhov.changelog.builder.gradle.plugin;

import ru.ezhov.changelog.builder.engine.infrastructure.DefaultConfiguration;

public class ChangelogBuilderPluginExtension {
    private String vcs = DefaultConfiguration.DEFAULT_VCS;

    private String changelogFileDirectory = DefaultConfiguration.CHANGELOG_FILE_DIRECTORY;

    private String changelogFilename = DefaultConfiguration.CHANGELOG_FILENAME;

    private String commitDateFormat = DefaultConfiguration.COMMIT_DATE_FORMAT;

    private String commitDateTimeFormat = DefaultConfiguration.COMMIT_DATE_TIME_FORMAT;

    private String template = null;

    public String getVcs() {
        return vcs;
    }

    public void setVcs(String vcs) {
        this.vcs = vcs;
    }

    public String getChangelogFileDirectory() {
        return changelogFileDirectory;
    }

    public void setChangelogFileDirectory(String changelogFileDirectory) {
        this.changelogFileDirectory = changelogFileDirectory;
    }

    public String getChangelogFilename() {
        return changelogFilename;
    }

    public void setChangelogFilename(String changelogFilename) {
        this.changelogFilename = changelogFilename;
    }

    public String getCommitDateFormat() {
        return commitDateFormat;
    }

    public void setCommitDateFormat(String commitDateFormat) {
        this.commitDateFormat = commitDateFormat;
    }

    public String getCommitDateTimeFormat() {
        return commitDateTimeFormat;
    }

    public void setCommitDateTimeFormat(String commitDateTimeFormat) {
        this.commitDateTimeFormat = commitDateTimeFormat;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "vcs='" + vcs + '\'' +
                ", changelogFileDirectory='" + changelogFileDirectory + '\'' +
                ", changelogFilename='" + changelogFilename + '\'' +
                ", commitDateFormat='" + commitDateFormat + '\'' +
                ", commitDateTimeFormat='" + commitDateTimeFormat + '\'' +
                ", template='" + template + '\'';
    }
}
