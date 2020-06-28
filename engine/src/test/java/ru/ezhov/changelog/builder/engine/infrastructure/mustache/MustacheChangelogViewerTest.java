package ru.ezhov.changelog.builder.engine.infrastructure.mustache;

import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.engine.domain.Commit;
import ru.ezhov.changelog.builder.engine.domain.CommitDateFormat;
import ru.ezhov.changelog.builder.engine.domain.CommitDateTime;
import ru.ezhov.changelog.builder.engine.domain.CommitDateTimeFormat;
import ru.ezhov.changelog.builder.engine.domain.CommitUsername;
import ru.ezhov.changelog.builder.engine.domain.Description;
import ru.ezhov.changelog.builder.engine.domain.Scope;
import ru.ezhov.changelog.builder.engine.domain.Template;
import ru.ezhov.changelog.builder.engine.domain.Type;

import java.time.LocalDateTime;
import java.util.Arrays;

class MustacheChangelogViewerTest {
    @Test
    void shouldCreateTemplate() throws Exception {
        MustacheChangelogViewer mustacheChangelogViewer = new MustacheChangelogViewer();


        final String template = mustacheChangelogViewer.create(
                Template.create(
                        "{{#commitDays}}\n" +
                                "<h2>{{commitDate}}</h2>\n" +
                                "<ul>\n" +
                                "{{#logs}}\n" +
                                "<li>{{type}} - {{description}}</li>\n" +
                                "{{/logs}}\n" +
                                "</ul>\n" +
                                "{{/commitDays}}"
                ),
                CommitDateFormat.create("yyyy-MM-dd"),
                CommitDateTimeFormat.create("yyyy-MM-dd"),
                Arrays.asList(
                        Commit.create(
                                Type.create("feat"),
                                Scope.empty(),
                                Description.create("s"),
                                CommitUsername.create("1"),
                                CommitDateTime.create(LocalDateTime.now())
                        ),

                        Commit.create(
                                Type.create("feat"),
                                Scope.create("git"),
                                Description.create("test"),
                                CommitUsername.create("1"),
                                CommitDateTime.create(LocalDateTime.now())
                        )
                )
        );

        System.out.println(template);
    }

}