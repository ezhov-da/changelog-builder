package ru.ezhov.changelog.builder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import ru.ezhov.changelog.builder.application.ChangelogApplicationService;
import ru.ezhov.changelog.builder.application.ChangelogApplicationServiceException;
import ru.ezhov.changelog.builder.domain.Template;
import ru.ezhov.changelog.builder.domain.TemplateRepository;
import ru.ezhov.changelog.builder.domain.TemplateRepositoryException;
import ru.ezhov.changelog.builder.infrastructure.ChangelogRepositoryFactory;
import ru.ezhov.changelog.builder.infrastructure.ChangelogViewerFactory;
import ru.ezhov.changelog.builder.infrastructure.CommitRepositoryFactory;
import ru.ezhov.changelog.builder.infrastructure.CommitRepositoryFactoryException;
import ru.ezhov.changelog.builder.infrastructure.ConfigurationFactory;
import ru.ezhov.changelog.builder.infrastructure.TemplateRepositoryFactory;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option("t", "template", true, "file path to template or string template");
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            try {
                final Template template = getTemplate(cmd.getOptionValue("t"));
                ChangelogApplicationService changelogApplicationService = new ChangelogApplicationService(
                        CommitRepositoryFactory.getInstance(ConfigurationFactory.defaultConfiguration().vcs()),
                        ChangelogViewerFactory.mustache(),
                        ChangelogRepositoryFactory.fileChangelogRepository(
                                ConfigurationFactory.defaultConfiguration().changelogFileDirectory(),
                                ConfigurationFactory.defaultConfiguration().changelogFilename()
                        )
                );
                changelogApplicationService.create(
                        template,
                        ConfigurationFactory.defaultConfiguration().commitDateFormat(),
                        ConfigurationFactory.defaultConfiguration().commitDateTimeFormat()
                );
            } catch (ChangelogApplicationServiceException | CommitRepositoryFactoryException | TemplateRepositoryException e) {
                System.out.println("Error");
                e.printStackTrace();
                System.exit(1);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
    }

    private static Template getTemplate(String template) throws TemplateRepositoryException {
        TemplateRepository templateRepository;
        if (template == null || "".equals(template)) {
            templateRepository = TemplateRepositoryFactory.defaultMustacheTemplateRepository();
        } else {
            File file = new File(template);
            if (file.exists() && !file.isDirectory()) {
                templateRepository = TemplateRepositoryFactory.file(file);
            } else {
                templateRepository = TemplateRepositoryFactory.string(template);
            }
        }
        return templateRepository.get();
    }
}