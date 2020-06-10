package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.log.Log;
import ru.ezhov.changelog.builder.domain.log.LogRepository;
import ru.ezhov.changelog.builder.domain.log.LogRepositoryException;
import ru.ezhov.changelog.builder.domain.type.Type;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GitLogRepository implements LogRepository {

    @Override
    public List<Log> all(Set<Type> types) throws LogRepositoryException {
        try {
            final Process process = Runtime.getRuntime().exec("git log --oneline");
            try (Scanner scanner = new Scanner(process.getInputStream(), "UTF-8")) {
                while (scanner.hasNextLine()) {
                    String logLine = scanner.nextLine();

                    System.out.println(logLine);
                }
            }
        } catch (Exception e) {
            throw new LogRepositoryException("Ошибка получения логов", e);
        }

        return null;
    }
}
