package ru.ezhov.changelog.builder.infrastructure;

import org.junit.jupiter.api.Test;

class GitLogRepositoryTest {
    @Test
    public void shouldReadLog() throws Exception {
        GitLogRepository gitLogRepository = new GitLogRepository();
        gitLogRepository.all();
    }
}