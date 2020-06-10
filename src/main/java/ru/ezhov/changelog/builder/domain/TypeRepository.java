package ru.ezhov.changelog.builder.domain;

import java.util.Set;

public interface TypeRepository {
    Set<Type> all() throws TypeRepositoryException;
}
