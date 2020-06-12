package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.ChangelogFileDirectory;
import ru.ezhov.changelog.builder.domain.ChangelogFilename;
import ru.ezhov.changelog.builder.domain.ChangelogRepository;

public abstract class ChangelogRepositoryFactory {
    private ChangelogRepositoryFactory() {
    }

    public static ChangelogRepository fileChangelogRepository(ChangelogFileDirectory changelogFileDirectory, ChangelogFilename filename) {
        return new FileChangelogRepository(changelogFileDirectory, filename);
    }
}
