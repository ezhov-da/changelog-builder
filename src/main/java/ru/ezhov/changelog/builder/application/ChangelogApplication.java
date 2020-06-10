package ru.ezhov.changelog.builder.application;

import ru.ezhov.changelog.builder.domain.ChangelogService;
import ru.ezhov.changelog.builder.domain.ChangelogServiceException;
import ru.ezhov.changelog.builder.infrastructure.GitLogRepository;
import ru.ezhov.changelog.builder.infrastructure.TypeRepositoryFactory;
import ru.ezhov.changelog.builder.infrastructure.mustache.MustacheChangelogViewer;

public class ChangelogApplication {
    public void create() {
        ChangelogService changelogService = new ChangelogService(
                new GitLogRepository(),
                TypeRepositoryFactory.defaultRepository(),
                new MustacheChangelogViewer()
        );

        try {
            changelogService.create("");
        } catch (ChangelogServiceException e) {
            e.printStackTrace();
        }
    }
}
