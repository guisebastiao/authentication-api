package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record Id(UUID value) {
    public Id {
        Objects.requireNonNull(value, "ID cannot be null");
    }

    public static Id from(String value) {
        Objects.requireNonNull(value, "ID cannot be null");

        try {
            return new Id(UUID.fromString(value));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid ID", exception);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
