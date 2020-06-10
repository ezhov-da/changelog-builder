package ru.ezhov.changelog.builder.domain.type;

import java.util.Set;

public interface TypeRepository {
    Set<Type> all() throws TypeRepositoryException;
}
