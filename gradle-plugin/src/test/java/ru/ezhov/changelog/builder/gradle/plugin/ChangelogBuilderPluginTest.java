package ru.ezhov.changelog.builder.gradle.plugin;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class ChangelogBuilderPluginTest {
    @Test
    public void pluginRegistersATask() {
        Project project = ProjectBuilder.builder().withProjectDir(new File(".")).build();
        project.getPlugins().apply("ru.ezhov.changelog.builder.gradle.plugin");

        assertNotNull(project.getTasks().findByName("changelogBuild"));
    }
}
