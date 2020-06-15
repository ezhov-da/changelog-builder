package ru.ezhov.changelog.builder.engine.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.changelog.builder.engine.domain.ChangelogFileDirectory;
import ru.ezhov.changelog.builder.engine.domain.ChangelogFilename;
import ru.ezhov.changelog.builder.engine.domain.ChangelogRepository;
import ru.ezhov.changelog.builder.engine.domain.ChangelogRepositoryException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class FileChangelogRepository implements ChangelogRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FileChangelogRepository.class);
    private final ChangelogFileDirectory changelogFileDirectory;
    private final ChangelogFilename changelogFilename;

    public FileChangelogRepository(ChangelogFileDirectory changelogFileDirectory, ChangelogFilename changelogFilename) {
        this.changelogFileDirectory = changelogFileDirectory;
        this.changelogFilename = changelogFilename;
    }

    @Override
    public void save(String template) throws ChangelogRepositoryException {
        final File file = new File(changelogFileDirectory.value(), changelogFilename.value());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(template.getBytes());

            LOG.info("File saved to '{}'", file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new ChangelogRepositoryException("File '" + changelogFilename + "' not found", e);
        } catch (IOException e) {
            throw new ChangelogRepositoryException("Error write file", e);
        }
    }
}
