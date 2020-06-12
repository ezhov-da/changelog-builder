package ru.ezhov.changelog.builder.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.changelog.builder.domain.Template;
import ru.ezhov.changelog.builder.domain.TemplateRepository;
import ru.ezhov.changelog.builder.domain.TemplateRepositoryException;

import java.io.InputStream;

class DefaultMustacheTemplateRepository implements TemplateRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMustacheTemplateRepository.class);
    private static final String template = "/default-template.md.mustache";

    @Override
    public Template get() throws TemplateRepositoryException {
        try (InputStream inputStream = getClass().getResourceAsStream(template)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            String template = new String(bytes);

            LOG.debug("Usage default template {}", template);

            return Template.create(template);
        } catch (Exception e) {
            throw new TemplateRepositoryException("Error read default Mustache template from resource '" + template + "'", e);
        }
    }
}
