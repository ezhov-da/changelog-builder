package ru.ezhov.changelog.builder.application;

import org.junit.jupiter.api.Test;

class ChangelogApplicationTest {
    @Test
    void shouldCreateLog() {
        ChangelogApplication changelogApplication = new ChangelogApplication();

        changelogApplication.create();
    }
}