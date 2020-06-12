package ru.ezhov.changelog.builder.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.domain.Log;

import java.util.Optional;

class GitLogParserTest {
    @Test
    void test() {
        final Optional<Log> optionalLogWithScope = new GitLogParser("bbc3c2b\tezhov_da\t2020-06-10T22:03:23+03:00\tfeat(git): implemented Git repository").parse();

        assertTrue(optionalLogWithScope.isPresent());

        final Log logWithScope = optionalLogWithScope.get();
        assertEquals("feat", logWithScope.type().value());
        assertTrue(logWithScope.scope().value().isPresent());
        assertEquals("git", logWithScope.scope().value().get());
        assertEquals("implemented Git repository", logWithScope.description().value());

        final Optional<Log> optionalLogWithoutScope = new GitLogParser("ebcc752\tezhov_da\t2020-06-10T21:45:06+03:00\tfeat: project initialized").parse();

        assertTrue(optionalLogWithoutScope.isPresent());

        final Log logWithoutScope = optionalLogWithoutScope.get();
        assertEquals("feat", logWithoutScope.type().value());
        assertFalse(logWithoutScope.scope().value().isPresent());
        assertEquals("project initialized", logWithoutScope.description().value());
    }
}