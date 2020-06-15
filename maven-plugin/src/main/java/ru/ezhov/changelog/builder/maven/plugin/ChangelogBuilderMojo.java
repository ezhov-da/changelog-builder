package ru.ezhov.changelog.builder.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import ru.ezhov.changelog.builder.engine.application.ChangelogApplicationService;
import ru.ezhov.changelog.builder.engine.application.ChangelogApplicationServiceException;
import ru.ezhov.changelog.builder.engine.domain.ChangelogFileDirectory;
import ru.ezhov.changelog.builder.engine.domain.ChangelogFilename;
import ru.ezhov.changelog.builder.engine.domain.CommitDateFormat;
import ru.ezhov.changelog.builder.engine.domain.CommitDateTimeFormat;
import ru.ezhov.changelog.builder.engine.domain.TemplateRepository;
import ru.ezhov.changelog.builder.engine.domain.TemplateRepositoryException;
import ru.ezhov.changelog.builder.engine.domain.Vcs;
import ru.ezhov.changelog.builder.engine.infrastructure.ChangelogRepositoryFactory;
import ru.ezhov.changelog.builder.engine.infrastructure.ChangelogViewerFactory;
import ru.ezhov.changelog.builder.engine.infrastructure.CommitRepositoryFactory;
import ru.ezhov.changelog.builder.engine.infrastructure.CommitRepositoryFactoryException;
import ru.ezhov.changelog.builder.engine.infrastructure.DefaultConfiguration;
import ru.ezhov.changelog.builder.engine.infrastructure.TemplateRepositoryFactory;

@Mojo(name = "generate")
public class ChangelogBuilderMojo extends AbstractMojo {
    @Parameter(defaultValue = DefaultConfiguration.DEFAULT_VCS)
    private String vcs;

    @Parameter(defaultValue = DefaultConfiguration.CHANGELOG_FILE_DIRECTORY)
    String changelogFileDirectory;

    @Parameter(defaultValue = DefaultConfiguration.CHANGELOG_FILENAME)
    String changelogFilename;

    @Parameter(defaultValue = DefaultConfiguration.COMMIT_DATE_FORMAT)
    String commitDateFormat;

    @Parameter(defaultValue = DefaultConfiguration.COMMIT_DATE_TIME_FORMAT)
    String commitDateTimeFormat;

    @Parameter
    String template;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Begin 'CHANGELOG.md' generation");

        try {
            final TemplateRepository templateRepository = (template == null || "".equals(template)) ?
                    TemplateRepositoryFactory.defaultMustacheTemplateRepository() : TemplateRepositoryFactory.string(template);

            final ChangelogApplicationService changelogApplicationService = new ChangelogApplicationService(
                    CommitRepositoryFactory.getInstance(Vcs.valueOf(vcs)),
                    ChangelogViewerFactory.mustache(),
                    ChangelogRepositoryFactory.fileChangelogRepository(
                            ChangelogFileDirectory.create(changelogFileDirectory),
                            ChangelogFilename.create(changelogFilename)
                    )
            );
            changelogApplicationService.create(
                    templateRepository.get(),
                    CommitDateFormat.create(commitDateFormat),
                    CommitDateTimeFormat.create(commitDateTimeFormat)

            );

            getLog().info("CHANGELOG.md generated");
        } catch (ChangelogApplicationServiceException | TemplateRepositoryException | CommitRepositoryFactoryException e) {
            throw new MojoFailureException("Error generation file", e);
        }
    }
}
