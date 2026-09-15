package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Objects;

public record IdentifierHash(String value) {
    private static final int MAX_LENGTH = 80;

    public IdentifierHash {
        Objects.requireNonNull(value, "IdentifierHash cannot be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("IdentifierHash cannot be empty");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The IdentifierHash must have a maximum of 80 characters");
        }
    }
}
