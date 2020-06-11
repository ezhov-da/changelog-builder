package ru.ezhov.changelog.builder.domain;

import java.util.List;

public class ChangelogService {
    private final LogRepository logRepository;
    private final ChangelogViewer changelogViewer;

    public ChangelogService(LogRepository logRepository, ChangelogViewer changelogViewer) {
        this.logRepository = logRepository;
        this.changelogViewer = changelogViewer;
    }

    public String create(String template) throws ChangelogServiceException {
        try {
            final List<Log> logs = logRepository.all();
            return changelogViewer.create(template, logs);
        } catch (LogRepositoryException e) {
            throw new ChangelogServiceException("Ошибка при получении логов", e);
        } catch (ChangelogViewerException e) {
            throw new ChangelogServiceException("Ошибка в работе шаблонизатора", e);
        }
    }
}
