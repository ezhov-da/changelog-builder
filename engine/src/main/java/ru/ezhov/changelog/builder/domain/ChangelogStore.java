package ru.ezhov.changelog.builder.domain;

public interface ChangelogStore {
    void save(String template)throws ChangelogStoreException;
}
