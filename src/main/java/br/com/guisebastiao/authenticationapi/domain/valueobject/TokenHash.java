package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Objects;

public record TokenHash(String value) {
    private static final int MAX_LENGTH = 80;

    public TokenHash {
        Objects.requireNonNull(value, "TokenHash cannot be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("TokenHash cannot be empty");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The TokenHash must have a maximum of 80 characters");
        }
    }
}
