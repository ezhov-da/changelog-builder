package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.ChangelogViewer;
import ru.ezhov.changelog.builder.infrastructure.mustache.MustacheChangelogViewer;

public abstract class ChangelogViewerFactory {
    private ChangelogViewerFactory() {
    }

    public static ChangelogViewer mustache() {
        return new MustacheChangelogViewer();
    }
}
