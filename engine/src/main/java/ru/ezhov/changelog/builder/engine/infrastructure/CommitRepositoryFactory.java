package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.CommitRepository;
import ru.ezhov.changelog.builder.engine.domain.Vcs;

public abstract class CommitRepositoryFactory {
    private CommitRepositoryFactory() {
    }

    public static CommitRepository getInstance(Vcs vcs) throws CommitRepositoryFactoryException {
        if (vcs == Vcs.GIT) {
            return new GitCommitRepository();
        }
        throw new CommitRepositoryFactoryException("Unsupported VCS. Support systems [" + Vcs.GIT + "]");

    }
}
