package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Locale;
import java.util.Objects;

public record RoleName(String value) {
    private static final int MAX_LENGTH = 50;

    public RoleName {
        Objects.requireNonNull(value, "Name cannot be null");

        value = value.trim().toLowerCase(Locale.ROOT);

        if (value.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The name must have a maximum of 255 characters");
        }
    }
}
