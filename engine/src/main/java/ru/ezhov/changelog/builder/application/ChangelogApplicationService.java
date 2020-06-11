package ru.ezhov.changelog.builder.application;

import ru.ezhov.changelog.builder.domain.ChangelogService;
import ru.ezhov.changelog.builder.domain.ChangelogServiceException;
import ru.ezhov.changelog.builder.infrastructure.CurrentDirectoryChangelogStore;
import ru.ezhov.changelog.builder.infrastructure.GitLogRepository;
import ru.ezhov.changelog.builder.infrastructure.mustache.MustacheChangelogViewer;

public class ChangelogApplicationService {
    public void create(String template) throws ChangelogApplicationServiceException{
        ChangelogService changelogService = new ChangelogService(
                new GitLogRepository(),
                new MustacheChangelogViewer(),
                new CurrentDirectoryChangelogStore("./CHANGELOG.md")
        );

        try {
            changelogService.create(template);
        } catch (ChangelogServiceException e) {
            throw new ChangelogApplicationServiceException("Error on created changelog", e);
        }
    }
}
