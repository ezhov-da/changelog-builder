package ru.ezhov.changelog.builder.domain;

import java.util.List;
import java.util.Set;

public interface LogRepository {
    List<Log> all(Set<Type> types) throws LogRepositoryException;
}
