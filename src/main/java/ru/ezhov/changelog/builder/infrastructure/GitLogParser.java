package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.CommitDateTime;
import ru.ezhov.changelog.builder.domain.CommitUsername;
import ru.ezhov.changelog.builder.domain.Description;
import ru.ezhov.changelog.builder.domain.Log;
import ru.ezhov.changelog.builder.domain.Scope;
import ru.ezhov.changelog.builder.domain.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class GitLogParser {
    private final String line;

    public GitLogParser(String line) {
        this.line = line;
    }

    public Optional<Log> parse() {
        String[] parts = line.split("\\t");
        String username = parts[1];
        LocalDateTime dateCommit = LocalDateTime.parse(parts[2], DateTimeFormatter.ISO_DATE_TIME);
        String commitText = parts[3];
        return parseCommitText(username, dateCommit, commitText);
    }

    private Optional<Log> parseCommitText(String username, LocalDateTime commmitDate, String commitText) {
        Log log = null;

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

            log = Log.create(
                    Type.create(type),
                    Scope.create(scope),
                    Description.create(textMessage),
                    CommitUsername.create(username),
                    CommitDateTime.create(commmitDate)
            );
        }

        return Optional.ofNullable(log);
    }
}
