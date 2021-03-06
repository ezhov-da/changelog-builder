package ru.ezhov.changelog.builder.engine.infrastructure;

import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.engine.domain.Commit;

import java.util.List;

class GitCommitRepositoryTest {
    @Test
    public void shouldReadLog() throws Exception {
        GitCommitRepository gitLogRepository = new GitCommitRepository();
        final List<Commit> all = gitLogRepository.all();
        all.forEach(log -> System.out.println(log.description().value()));
    }
}