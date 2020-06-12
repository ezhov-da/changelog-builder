package ru.ezhov.changelog.builder.infrastructure.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import ru.ezhov.changelog.builder.domain.ChangelogViewer;
import ru.ezhov.changelog.builder.domain.ChangelogViewerException;
import ru.ezhov.changelog.builder.domain.Commit;
import ru.ezhov.changelog.builder.domain.CommitDateFormat;
import ru.ezhov.changelog.builder.domain.CommitDateTimeFormat;
import ru.ezhov.changelog.builder.domain.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MustacheChangelogViewer implements ChangelogViewer {
    @Override
    public String create(
            Template template,
            CommitDateFormat commitDateFormat,
            CommitDateTimeFormat commitDateTimeFormat,
            List<Commit> commits
    ) throws ChangelogViewerException {
        final Map<LocalDate, List<Commit>> collect = commits.stream().collect(Collectors.groupingBy(Commit::commitDate));

        final List<CommitDayMustache> commitDayMustaches = collect
                .entrySet()
                .stream()
                .map(
                        e -> new CommitDayMustache(
                                e.getKey(),
                                commitDateFormat,
                                e.getValue()
                                        .stream()
                                        .sorted((o1, o2) -> o2.commitDateTime().value().compareTo(o1.commitDateTime().value()))
                                        .map(commit -> new CommitMustache(commit, commitDateTimeFormat))
                                        .collect(Collectors.toList())
                        )
                )
                .sorted((o1, o2) -> o2.getCommitDate().compareTo(o1.getCommitDate()))
                .collect(Collectors.toList());
        try {
            MustacheFactory mf = new DefaultMustacheFactory();
            StringReader stringReader = new StringReader(template.value());
            Mustache mustache = mf.compile(stringReader, null);
            StringWriter stringWriter = new StringWriter();
            mustache.execute(stringWriter, new CommitsMustache(commitDayMustaches)).flush();
            return stringWriter.toString();
        } catch (Exception e) {
            throw new ChangelogViewerException("Error template build", e);
        }
    }
}
