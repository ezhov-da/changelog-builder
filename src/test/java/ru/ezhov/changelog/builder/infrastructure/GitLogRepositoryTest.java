package ru.ezhov.changelog.builder.infrastructure;

import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.domain.Type;

import java.util.HashSet;
import java.util.Set;

class GitLogRepositoryTest {
    @Test
    public void shouldReadLog() throws Exception {
        Set<Type> types = new HashSet<>();
        types.add(Type.create("feat"));

        GitLogRepository gitLogRepository = new GitLogRepository();
        gitLogRepository.all(types);
    }
}