package ru.ezhov.changelog.builder.gradle.plugin;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
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
import ru.ezhov.changelog.builder.engine.infrastructure.TemplateRepositoryFactory;

public class ChangelogBuilderPlugin implements Plugin<Project> {

    public void apply(Project project) {
        ChangelogBuilderPluginExtension extension = project.getExtensions()
                .create("changelogBuild", ChangelogBuilderPluginExtension.class);

        project.getLogger().debug("Plugin properties {}", extension.toString());

        project.getTasks().register("changelogBuild", task -> task.doLast(s -> {
            project.getLogger().info("Begin 'CHANGELOG.md' generation");

            try {
                final TemplateRepository templateRepository = (extension.getTemplate() == null || "".equals(extension.getTemplate())) ?
                        TemplateRepositoryFactory.defaultMustacheTemplateRepository() : TemplateRepositoryFactory.string(extension.getTemplate());

                final ChangelogApplicationService changelogApplicationService = new ChangelogApplicationService(
                        CommitRepositoryFactory.getInstance(Vcs.valueOf(extension.getVcs())),
                        ChangelogViewerFactory.mustache(),
                        ChangelogRepositoryFactory.fileChangelogRepository(
                                ChangelogFileDirectory.create(extension.getChangelogFileDirectory()),
                                ChangelogFilename.create(extension.getChangelogFilename())
                        )
                );
                changelogApplicationService.create(
                        templateRepository.get(),
                        CommitDateFormat.create(extension.getCommitDateFormat()),
                        CommitDateTimeFormat.create(extension.getCommitDateTimeFormat())

                );

                project.getLogger().info("CHANGELOG.md generated");
            } catch (ChangelogApplicationServiceException | TemplateRepositoryException | CommitRepositoryFactoryException e) {
                e.printStackTrace();
                throw new RuntimeException("Error CHANGELOG.md generation file", e);
            }
        }));
    }
}
