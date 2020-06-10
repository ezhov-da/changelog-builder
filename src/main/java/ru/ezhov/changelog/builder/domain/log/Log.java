package ru.ezhov.changelog.builder.domain.log;

import ru.ezhov.changelog.builder.domain.type.Type;

public class Log {
    private final Type type;
    private final Scope scope;
    private final Description description;

    private Log(Type type, Scope scope, Description description) {
        this.type = type;
        this.scope = scope;
        this.description = description;
    }

    public Log create(Type type, Scope scope, Description description) {
        return new Log(type, scope, description);
    }

    public Type type() {
        return type;
    }

    public Scope scope() {
        return scope;
    }

    public Description description() {
        return description;
    }
}
