package ru.ezhov.changelog.builder.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import ru.ezhov.changelog.builder.application.ChangelogApplicationService;

import java.io.IOException;
import java.io.InputStream;

@Mojo(name = "generate")
public class ChangelogBuilderMojo extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Begin 'CHANGELOG.md' generation");

        try (InputStream inputStream = ChangelogBuilderMojo.class.getResourceAsStream("/default-template.md.mustache")) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String template = new String(bytes);
            ChangelogApplicationService changelogApplicationService = new ChangelogApplicationService();
            changelogApplicationService.create(template);

            getLog().info("CHANGELOG.md generated");
        } catch (IOException e) {
            throw new MojoFailureException("Error generation file", e);
        } catch (Exception e) {
            throw new MojoExecutionException("Error generation file", e);
        }

    }
}
