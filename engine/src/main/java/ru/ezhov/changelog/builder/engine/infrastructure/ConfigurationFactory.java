package ru.ezhov.changelog.builder.engine.infrastructure;

import ru.ezhov.changelog.builder.engine.domain.Configuration;

public abstract class ConfigurationFactory {
    private ConfigurationFactory() {
    }

    public static Configuration defaultConfiguration() {
        return new DefaultConfiguration();
    }
}
