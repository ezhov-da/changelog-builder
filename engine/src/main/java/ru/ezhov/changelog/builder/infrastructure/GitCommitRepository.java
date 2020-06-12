package ru.ezhov.changelog.builder.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.changelog.builder.domain.Commit;
import ru.ezhov.changelog.builder.domain.CommitRepository;
import ru.ezhov.changelog.builder.domain.CommitRepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class GitCommitRepository implements CommitRepository {
    private static final Logger LOG = LoggerFactory.getLogger(GitCommitRepository.class);

    @Override
    public List<Commit> all() throws CommitRepositoryException {
        List<Commit> commitRows = new ArrayList<>();

        try {
            final Process process = Runtime.getRuntime().exec("git log --pretty=format:%h%x09%an%x09%ad%x09%s --date=iso-strict");
            try (Scanner scanner = new Scanner(process.getInputStream(), "UTF-8")) {
                while (scanner.hasNextLine()) {
                    String logLine = scanner.nextLine();
                    GitCommitParser gitCommitParser = new GitCommitParser(logLine);
                    final Optional<Commit> log = gitCommitParser.parse();
                    if (log.isPresent()) {
                        commitRows.add(log.get());
                    } else {
                        LOG.warn("Not found convention commits in row '{}'. See 'https://www.conventionalcommits.org/en/v1.0.0/'", logLine);
                    }
                }
            }
        } catch (Exception e) {
            throw new CommitRepositoryException("Error log", e);
        }

        return commitRows;
    }
}
