package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.Template;
import ru.ezhov.changelog.builder.engine.domain.TemplateRepository;
import ru.ezhov.changelog.builder.engine.domain.TemplateRepositoryException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

public class FileTemplateRepository implements TemplateRepository {
    private final File file;

    public FileTemplateRepository(File file) {
        this.file = file;
    }

    @Override
    public Template get() throws TemplateRepositoryException {
        try {
            final byte[] bytes = Files.readAllBytes(file.toPath());
            return Template.create(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new TemplateRepositoryException("Unsupported encoding", e);
        } catch (IOException e) {
            throw new TemplateRepositoryException("Template file read error", e);
        }
    }
}
