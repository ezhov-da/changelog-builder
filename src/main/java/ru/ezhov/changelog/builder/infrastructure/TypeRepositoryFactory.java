package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.TypeRepository;

public class TypeRepositoryFactory {

    public static TypeRepository defaultRepository() {
        return new DefaultResourceTypeRepository();
    }
}
