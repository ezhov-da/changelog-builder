package ru.ezhov.changelog.builder.infrastructure.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import ru.ezhov.changelog.builder.domain.ChangelogViewer;
import ru.ezhov.changelog.builder.domain.ChangelogViewerException;
import ru.ezhov.changelog.builder.domain.Log;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MustacheChangelogViewer implements ChangelogViewer {
    @Override
    public String create(String template, List<Log> logs) throws ChangelogViewerException {
        final Map<LocalDate, List<Log>> collect = logs.stream().collect(Collectors.groupingBy(Log::commitDate));

        final List<CommitDayMustache> commitDayMustaches = collect
                .entrySet()
                .stream()
                .map(
                        e -> new CommitDayMustache(
                                e.getKey(),
                                e.getValue()
                                        .stream()
                                        .map(LogMustache::new)
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());


        try {
            MustacheFactory mf = new DefaultMustacheFactory();
            StringReader stringReader = new StringReader(template);
            Mustache mustache = mf.compile(stringReader, null);
            StringWriter stringWriter = new StringWriter();
            mustache.execute(stringWriter, new LogsMustache(commitDayMustaches)).flush();
            return stringWriter.toString();
        } catch (Exception e) {
            throw new ChangelogViewerException("Ошибка при подготовке шаблона", e);
        }
    }
}
