package ru.ezhov.changelog.builder.domain;

import java.util.Objects;

public class Type {
    private final String value;

    private Type(String value) {
        this.value = value;
    }

    public static Type create(String value) {
        return new Type(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(value, type.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
