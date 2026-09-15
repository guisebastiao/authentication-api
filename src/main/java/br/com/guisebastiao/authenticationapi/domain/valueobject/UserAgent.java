package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Locale;
import java.util.Objects;

public record UserAgent(String value) {
    public UserAgent {
        Objects.requireNonNull(value);

        value = value.trim().toLowerCase(Locale.ROOT);

        if (value.isBlank()) {
            throw new IllegalArgumentException("User agent cannot be empty");
        }
    }
}
