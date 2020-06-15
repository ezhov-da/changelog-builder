package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.ChangelogViewer;
import ru.ezhov.changelog.builder.engine.infrastructure.mustache.MustacheChangelogViewer;

public abstract class ChangelogViewerFactory {
    private ChangelogViewerFactory() {
    }

    public static ChangelogViewer mustache() {
        return new MustacheChangelogViewer();
    }
}
