package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Objects;

public record OptHash(String value) {
    private static final int MAX_LENGTH = 80;

    public OptHash {
        Objects.requireNonNull(value, "OptHash cannot be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException("OptHash cannot be empty");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The OptHash must have a maximum of 80 characters");
        }
    }
}
