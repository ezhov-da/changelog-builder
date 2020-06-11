package ru.ezhov.changelog.builder;

import ru.ezhov.changelog.builder.application.ChangelogApplicationService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        try {
            final Optional<String> template = getTemplate(args);
            if (template.isPresent()) {
                ChangelogApplicationService changelogApplicationService = new ChangelogApplicationService();
                changelogApplicationService.create(template.get());
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
    }

    private static Optional<String> getTemplate(String[] args) throws IOException {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-f")) {
                    if ((i + 1) <= args.length) {
                        String path = args[i + 1];

                        final byte[] bytes = Files.readAllBytes(new File(path).toPath());
                        return Optional.of(new String(bytes));
                    }
                } else if (args[i].equals("-t")) {
                    if ((i + 1) <= args.length) {
                        return Optional.of(args[i + 1]);
                    }
                }
            }
        } else {
            try (InputStream inputStream = Application.class.getResourceAsStream("/default-template.md.mustache")) {
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                return Optional.of(new String(bytes));
            }
        }
        return Optional.empty();
    }
}
