package ru.ezhov.changelog.builder.domain;

import java.util.List;

public class ChangelogService {
    private final LogRepository logRepository;
    private final ChangelogViewer changelogViewer;
    private final ChangelogStore changelogStore;

    public ChangelogService(LogRepository logRepository, ChangelogViewer changelogViewer, ChangelogStore changelogStore) {
        this.logRepository = logRepository;
        this.changelogViewer = changelogViewer;
        this.changelogStore = changelogStore;
    }

    public void create(String template) throws ChangelogServiceException {
        try {
            final List<Log> logs = logRepository.all();
            final String changelog = changelogViewer.create(template, logs);
            changelogStore.save(changelog);
        } catch (LogRepositoryException e) {
            throw new ChangelogServiceException("Ошибка при получении логов", e);
        } catch (ChangelogViewerException e) {
            throw new ChangelogServiceException("Ошибка в работе шаблонизатора", e);
        } catch (ChangelogStoreException e) {
            throw new ChangelogServiceException("Error on save file", e);
        }
    }
}
