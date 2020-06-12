package ru.ezhov.changelog.builder.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.changelog.builder.domain.Log;
import ru.ezhov.changelog.builder.domain.LogRepository;
import ru.ezhov.changelog.builder.domain.LogRepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GitLogRepository implements LogRepository {
    private static final Logger LOG = LoggerFactory.getLogger(GitLogRepository.class);

    @Override
    public List<Log> all() throws LogRepositoryException {
        List<Log> logRows = new ArrayList<>();

        try {
            final Process process = Runtime.getRuntime().exec("git log --pretty=format:%h%x09%an%x09%ad%x09%s --date=iso-strict");
            try (Scanner scanner = new Scanner(process.getInputStream(), "UTF-8")) {
                while (scanner.hasNextLine()) {
                    String logLine = scanner.nextLine();
                    GitLogParser gitLogParser = new GitLogParser(logLine);
                    final Optional<Log> log = gitLogParser.parse();
                    if (log.isPresent()) {
                        logRows.add(log.get());
                    } else {
                        LOG.warn("Not found convention commits in row '{}'. See 'https://www.conventionalcommits.org/en/v1.0.0/'", logLine);
                    }
                }
            }
        } catch (Exception e) {
            throw new LogRepositoryException("Error log", e);
        }

        return logRows;
    }
}
