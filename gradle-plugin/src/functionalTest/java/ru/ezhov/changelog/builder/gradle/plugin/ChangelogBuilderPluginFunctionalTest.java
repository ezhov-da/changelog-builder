package ru.ezhov.changelog.builder.gradle.plugin;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.nio.file.Files;

import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.BuildResult;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangelogBuilderPluginFunctionalTest {
    @Test
    @Ignore
    public void canRunTask() throws IOException {
        File projectDir = new File("build/functionalTest");
        Files.createDirectories(projectDir.toPath());
        writeString(new File(projectDir, "settings.gradle"), "");
        writeString(new File(projectDir, "build.gradle.kts"),
                "plugins {" +
                        "  id(\"ru.ezhov.changelog.builder.gradle.plugin\")" +
                        "}");

        GradleRunner runner = GradleRunner.create();
        runner.forwardOutput();
        runner.withPluginClasspath();
        runner.withArguments("changelogBuild");
        runner.withProjectDir(projectDir);
        BuildResult result = runner.build();

        // Verify the result
//        assertTrue(result.getOutput().contains("Hello from plugin 'ru.ezhov.changelog.builder.maven.plugin[D[D[D[D[D[D[D[D[D[D[D[D[D[C[C[C[Cgradle.[3~[3~[3~[3~[3~[3~p[2~lugin.greeting'"));
    }

    private void writeString(File file, String string) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(string);
        }
    }
}
