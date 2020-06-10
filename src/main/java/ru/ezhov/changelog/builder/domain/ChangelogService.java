package ru.ezhov.changelog.builder.domain;

import java.util.List;

public class ChangelogService {
    private final LogRepository logRepository;
    private final TypeRepository typeRepository;
    private final ChangelogViewer changelogViewer;

    public ChangelogService(LogRepository logRepository, TypeRepository typeRepository, ChangelogViewer changelogViewer) {
        this.logRepository = logRepository;
        this.typeRepository = typeRepository;
        this.changelogViewer = changelogViewer;
    }

    public void create(String template) throws ChangelogServiceException {
        try {
            final List<Log> logs = logRepository.all(typeRepository.all());
            changelogViewer.create(template, logs);
        } catch (TypeRepositoryException e) {
            throw new ChangelogServiceException("Ошибка при получении типов", e);
        } catch (LogRepositoryException e) {
            throw new ChangelogServiceException("Ошибка при получении логов", e);
        } catch (ChangelogViewerException e) {
            throw new ChangelogServiceException("Ошибка в работе шаблонизатора", e);
        }
    }
}
