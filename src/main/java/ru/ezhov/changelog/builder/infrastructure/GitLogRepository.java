package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.log.Log;
import ru.ezhov.changelog.builder.domain.log.LogRepository;
import ru.ezhov.changelog.builder.domain.log.LogRepositoryException;

import java.util.List;

public class GitLogRepository implements LogRepository {
    public List<Log> all() throws LogRepositoryException {
        return null;
    }
}
