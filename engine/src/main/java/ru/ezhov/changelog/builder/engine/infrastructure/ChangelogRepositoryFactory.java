package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.ChangelogFileDirectory;
import ru.ezhov.changelog.builder.engine.domain.ChangelogFilename;
import ru.ezhov.changelog.builder.engine.domain.ChangelogRepository;

public abstract class ChangelogRepositoryFactory {
    private ChangelogRepositoryFactory() {
    }

    public static ChangelogRepository fileChangelogRepository(ChangelogFileDirectory changelogFileDirectory, ChangelogFilename filename) {
        return new FileChangelogRepository(changelogFileDirectory, filename);
    }
}
