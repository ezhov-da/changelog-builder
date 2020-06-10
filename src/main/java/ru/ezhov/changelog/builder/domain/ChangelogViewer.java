package ru.ezhov.changelog.builder.domain;

import java.util.List;

public interface ChangelogViewer {
    String create(String template, List<Log> logs) throws ChangelogViewerException;
}
