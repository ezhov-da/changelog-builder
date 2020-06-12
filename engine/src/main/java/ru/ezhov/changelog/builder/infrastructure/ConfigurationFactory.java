package ru.ezhov.changelog.builder.infrastructure;

import ru.ezhov.changelog.builder.domain.Configuration;

public abstract class ConfigurationFactory {
    private ConfigurationFactory() {
    }

    public static Configuration defaultConfiguration() {
        return new DefaultConfiguration();
    }
}
