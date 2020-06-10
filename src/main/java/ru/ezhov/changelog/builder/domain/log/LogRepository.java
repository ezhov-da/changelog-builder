package ru.ezhov.changelog.builder.domain.log;

import ru.ezhov.changelog.builder.domain.type.Type;

import java.util.List;
import java.util.Set;

public interface LogRepository {
    List<Log> all(Set<Type> types) throws LogRepositoryException;
}
