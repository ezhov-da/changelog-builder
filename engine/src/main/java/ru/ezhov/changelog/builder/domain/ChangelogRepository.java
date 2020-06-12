package ru.ezhov.changelog.builder.domain;

public interface ChangelogRepository {
    void save(String template) throws ChangelogRepositoryException;
}
