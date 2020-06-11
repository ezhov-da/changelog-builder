package ru.ezhov.changelog.builder.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.changelog.builder.domain.ChangelogStore;
import ru.ezhov.changelog.builder.domain.ChangelogStoreException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CurrentDirectoryChangelogStore implements ChangelogStore {
    private static final Logger LOG = LoggerFactory.getLogger(CurrentDirectoryChangelogStore.class);
    private final String filename;

    public CurrentDirectoryChangelogStore(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(String template) throws ChangelogStoreException {
        final File file = new File(filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(template.getBytes());

            LOG.info("File saved to '{}'", file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new ChangelogStoreException("File '" + filename + "' not found", e);
        } catch (IOException e) {
            throw new ChangelogStoreException("Error write file", e);
        }
    }
}
