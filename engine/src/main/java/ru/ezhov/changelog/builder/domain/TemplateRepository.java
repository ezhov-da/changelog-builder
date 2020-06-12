package ru.ezhov.changelog.builder.domain;

public interface TemplateRepository {
    Template get() throws TemplateRepositoryException;
}
