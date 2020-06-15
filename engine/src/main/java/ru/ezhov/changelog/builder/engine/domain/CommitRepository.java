package ru.ezhov.changelog.builder.engine.domain;

import java.util.List;

public interface CommitRepository {
    List<Commit> all() throws CommitRepositoryException;
}
