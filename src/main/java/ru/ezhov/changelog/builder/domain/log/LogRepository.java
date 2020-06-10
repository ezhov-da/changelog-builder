package ru.ezhov.changelog.builder.domain.log;

import java.util.List;

public interface LogRepository {
    List<Log> all() throws LogRepositoryException;
}
