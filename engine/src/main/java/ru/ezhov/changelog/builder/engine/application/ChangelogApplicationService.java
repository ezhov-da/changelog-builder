package ru.ezhov.changelog.builder.engine.application;

import ru.ezhov.changelog.builder.engine.domain.ChangelogRepository;
import ru.ezhov.changelog.builder.engine.domain.ChangelogService;
import ru.ezhov.changelog.builder.engine.domain.ChangelogServiceException;
import ru.ezhov.changelog.builder.engine.domain.ChangelogViewer;
import ru.ezhov.changelog.builder.engine.domain.CommitDateFormat;
import ru.ezhov.changelog.builder.engine.domain.CommitDateTimeFormat;
import ru.ezhov.changelog.builder.engine.domain.CommitRepository;
import ru.ezhov.changelog.builder.engine.domain.Template;

public class ChangelogApplicationService {
    private final CommitRepository commitRepository;
    private final ChangelogViewer changelogViewer;
    private final ChangelogRepository changelogRepository;

    public ChangelogApplicationService(CommitRepository commitRepository, ChangelogViewer changelogViewer, ChangelogRepository changelogRepository) {
        this.commitRepository = commitRepository;
        this.changelogViewer = changelogViewer;
        this.changelogRepository = changelogRepository;
    }

    public void create(Template template, CommitDateFormat commitDateFormat, CommitDateTimeFormat commitDateTimeFormat) throws ChangelogApplicationServiceException {
        ChangelogService changelogService = new ChangelogService(
                commitRepository,
                changelogViewer,
                changelogRepository
        );

        try {
            changelogService.create(template, commitDateFormat, commitDateTimeFormat);
        } catch (ChangelogServiceException e) {
            throw new ChangelogApplicationServiceException("Error on created changelog", e);
        }
    }
}
