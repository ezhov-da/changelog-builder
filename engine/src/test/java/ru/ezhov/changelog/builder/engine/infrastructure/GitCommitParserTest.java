package ru.ezhov.changelog.builder.engine.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.engine.domain.Commit;

import java.util.Optional;

class GitCommitParserTest {
    @Test
    void test() {
        final Optional<Commit> optionalLogWithScope = new GitCommitParser("bbc3c2b\tezhov_da\t2020-06-10T22:03:23+03:00\tfeat(git): implemented Git repository").parse();

        assertTrue(optionalLogWithScope.isPresent());

        final Commit commitWithScope = optionalLogWithScope.get();
        assertEquals("feat", commitWithScope.type().value());
        assertTrue(commitWithScope.scope().value().isPresent());
        assertEquals("git", commitWithScope.scope().value().get());
        assertEquals("implemented Git repository", commitWithScope.description().value());

        final Optional<Commit> optionalLogWithoutScope = new GitCommitParser("ebcc752\tezhov_da\t2020-06-10T21:45:06+03:00\tfeat: project initialized").parse();

        assertTrue(optionalLogWithoutScope.isPresent());

        final Commit commitWithoutScope = optionalLogWithoutScope.get();
        assertEquals("feat", commitWithoutScope.type().value());
        assertFalse(commitWithoutScope.scope().value().isPresent());
        assertEquals("project initialized", commitWithoutScope.description().value());
    }
}