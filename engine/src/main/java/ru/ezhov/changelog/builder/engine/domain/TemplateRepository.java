package ru.ezhov.changelog.builder.engine.domain;

public interface TemplateRepository {
    Template get() throws TemplateRepositoryException;
}
