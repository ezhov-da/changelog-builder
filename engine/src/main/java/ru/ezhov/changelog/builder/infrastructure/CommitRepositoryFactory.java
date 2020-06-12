package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.CommitRepository;
import ru.ezhov.changelog.builder.domain.Vcs;

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
