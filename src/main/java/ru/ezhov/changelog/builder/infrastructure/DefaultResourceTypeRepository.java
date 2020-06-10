package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.type.Type;
import ru.ezhov.changelog.builder.domain.type.TypeRepository;
import ru.ezhov.changelog.builder.domain.type.TypeRepositoryException;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DefaultResourceTypeRepository implements TypeRepository {
    private static final String DEFAULT_TYPE_FILE = "/default-types.txt";

    public Set<Type> all() throws TypeRepositoryException {
        Set<Type> types = new HashSet<>();
        try (Scanner scanner = new Scanner(this.getClass().getResourceAsStream(DEFAULT_TYPE_FILE), "UTF-8")) {
            while (scanner.hasNextLine()) {
                types.add(Type.create(scanner.nextLine()));
            }
        } catch (Exception e) {
            throw new TypeRepositoryException("Ошибка чтения файла " + DEFAULT_TYPE_FILE + " из ресурсов с типами коммитов по умолчанию", e);
        }
        return types;
    }
}
