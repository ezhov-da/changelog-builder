package ru.ezhov.changelog.builder.infrastructure.mustache;

import org.junit.jupiter.api.Test;
import ru.ezhov.changelog.builder.domain.CommitDateTime;
import ru.ezhov.changelog.builder.domain.CommitUsername;
import ru.ezhov.changelog.builder.domain.Description;
import ru.ezhov.changelog.builder.domain.Log;
import ru.ezhov.changelog.builder.domain.Scope;
import ru.ezhov.changelog.builder.domain.Type;

import java.time.LocalDateTime;
import java.util.Arrays;

class MustacheChangelogViewerTest {
    @Test
    void shouldCreateTemplate() throws Exception {
        MustacheChangelogViewer mustacheChangelogViewer = new MustacheChangelogViewer();


        final String template = mustacheChangelogViewer.create(
                "{{#commitDays}}\n" +
                        "<h2>{{commitDate}}</h2>\n" +
                        "<ul>\n" +
                        "{{#logs}}\n" +
                        "<li>{{type}} - {{description}}</li>\n" +
                        "{{/logs}}\n" +
                        "</ul>\n" +
                        "{{/commitDays}}",
                Arrays.asList(
                        Log.create(
                                Type.create("feat"),
                                Scope.empty(),
                                Description.create("s"),
                                CommitUsername.create("1"),
                                CommitDateTime.create(LocalDateTime.now())
                        ),

                        Log.create(
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