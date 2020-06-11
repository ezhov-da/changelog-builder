package ru.ezhov.changelog.builder.domain;

import java.util.List;

public interface LogRepository {
    List<Log> all() throws LogRepositoryException;
}
