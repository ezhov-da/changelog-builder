package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.type.Type;
import ru.ezhov.changelog.builder.domain.type.TypeRepositoryException;

import java.util.Set;

public class TypeFactory {
    public static Set<Type> defaultTypes() throws TypeFactoryException {
        try {
            return new DefaultResourceTypeRepository().all();
        } catch (TypeRepositoryException e) {
            throw new TypeFactoryException("Ошибка получения типов коммитов по умолчанию", e);
        }
    }
}
