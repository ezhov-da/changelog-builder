package ru.ezhov.changelog.builder.engine.domain;

import java.util.List;

public class ChangelogService {
    private final CommitRepository commitRepository;
    private final ChangelogViewer changelogViewer;
    private final ChangelogRepository changelogRepository;

    public ChangelogService(CommitRepository commitRepository, ChangelogViewer changelogViewer, ChangelogRepository changelogRepository) {
        this.commitRepository = commitRepository;
        this.changelogViewer = changelogViewer;
        this.changelogRepository = changelogRepository;
    }

    public void create(Template template, CommitDateFormat commitDateFormat, CommitDateTimeFormat commitDateTimeFormat) throws ChangelogServiceException {
        try {
            final List<Commit> commits = commitRepository.all();
            final String changelog = changelogViewer.create(template, commitDateFormat, commitDateTimeFormat, commits);
            changelogRepository.save(changelog);
        } catch (CommitRepositoryException e) {
            throw new ChangelogServiceException("Error commits retrieve", e);
        } catch (ChangelogViewerException e) {
            throw new ChangelogServiceException("Error template", e);
        } catch (ChangelogRepositoryException e) {
            throw new ChangelogServiceException("Error on save file", e);
        }
    }
}
