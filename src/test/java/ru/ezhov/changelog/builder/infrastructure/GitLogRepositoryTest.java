package ru.ezhov.changelog.builder.infrastructure;

import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.domain.Log;

import java.util.List;

class GitLogRepositoryTest {
    @Test
    public void shouldReadLog() throws Exception {
        GitLogRepository gitLogRepository = new GitLogRepository();
        final List<Log> all = gitLogRepository.all();
        all.forEach(log -> System.out.println(log.description().value()));
    }
}