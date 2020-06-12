package ru.ezhov.changelog.builder.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.changelog.builder.domain.Template;
import ru.ezhov.changelog.builder.domain.TemplateRepository;

class StringTemplateRepository implements TemplateRepository {
    private static final Logger LOG = LoggerFactory.getLogger(StringTemplateRepository.class);
    private final String template;

    public StringTemplateRepository(String template) {
        this.template = template;
    }

    @Override
    public Template get() {
        LOG.debug("Usage template '{}'", template);

        return Template.create(template);
    }
}
