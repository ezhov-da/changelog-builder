package ru.ezhov.changelog.builder.engine.domain;

public interface ChangelogRepository {
    void save(String template) throws ChangelogRepositoryException;
}
