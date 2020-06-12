package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.TemplateRepository;

public abstract class TemplateRepositoryFactory {
    private TemplateRepositoryFactory() {
    }

    public static TemplateRepository defaultMustacheTemplateRepository() {
        return new DefaultMustacheTemplateRepository();
    }

    public static TemplateRepository string(String template) {
        return new StringTemplateRepository(template);
    }
}
