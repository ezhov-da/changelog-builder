package ru.ezhov.changelog.builder.application;

import ru.ezhov.changelog.builder.domain.ChangelogService;
import ru.ezhov.changelog.builder.domain.ChangelogServiceException;
import ru.ezhov.changelog.builder.infrastructure.GitLogRepository;
import ru.ezhov.changelog.builder.infrastructure.mustache.MustacheChangelogViewer;

public class ChangelogApplicationService {
    public void create(String template) {
        ChangelogService changelogService = new ChangelogService(
                new GitLogRepository(),
                new MustacheChangelogViewer()
        );

        try {
            final String result = changelogService.create(template);
            System.out.println(result);
        } catch (ChangelogServiceException e) {
            e.printStackTrace();
        }
    }
}
