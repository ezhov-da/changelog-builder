package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.Commit;
import ru.ezhov.changelog.builder.engine.domain.CommitDateTime;
import ru.ezhov.changelog.builder.engine.domain.CommitUsername;
import ru.ezhov.changelog.builder.engine.domain.Description;
import ru.ezhov.changelog.builder.engine.domain.Scope;
import ru.ezhov.changelog.builder.engine.domain.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

class GitCommitParser {
    private final String line;

    public GitCommitParser(String line) {
        this.line = line;
    }

    public Optional<Commit> parse() {
        String[] parts = line.split("\\t");
        String username = parts[1];
        LocalDateTime dateCommit = LocalDateTime.parse(parts[2], DateTimeFormatter.ISO_DATE_TIME);
        String commitText = parts[3];
        return parseCommitText(username, dateCommit, commitText);
    }

    private Optional<Commit> parseCommitText(String username, LocalDateTime commitDate, String commitText) {
        Commit commit = null;

        final int indexOf = commitText.indexOf(':');
        if (indexOf != -1) {
            String textMessage = commitText.substring(indexOf + 1);

            String typeAndScope = commitText.substring(0, indexOf);

            final int indexOfOpen = typeAndScope.indexOf('(');
            final int indexOfClose = typeAndScope.indexOf(')');

            String type;
            String scope = null;
            if (indexOfOpen != -1 && indexOfClose != -1) {
                scope = typeAndScope.substring(indexOfOpen + 1, indexOfClose);
                type = typeAndScope.substring(0, indexOfOpen);
            } else {
                type = typeAndScope;
            }

            commit = Commit.create(
                    Type.create(type),
                    Scope.create(scope),
                    Description.create(textMessage),
                    CommitUsername.create(username),
                    CommitDateTime.create(commitDate)
            );
        }

        return Optional.ofNullable(commit);
    }
}
