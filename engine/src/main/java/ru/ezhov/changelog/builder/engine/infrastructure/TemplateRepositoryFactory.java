package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.TemplateRepository;

import java.io.File;

public abstract class TemplateRepositoryFactory {
    private TemplateRepositoryFactory() {
    }

    public static TemplateRepository defaultMustacheTemplateRepository() {
        return new DefaultMustacheTemplateRepository();
    }

    public static TemplateRepository string(String template) {
        return new StringTemplateRepository(template);
    }

    public static TemplateRepository file(File file) {
        return new FileTemplateRepository(file);
    }
}
