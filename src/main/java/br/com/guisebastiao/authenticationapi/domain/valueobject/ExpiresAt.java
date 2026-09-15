package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;
import java.util.Objects;

public record ExpiresAt(Instant value) {
    public ExpiresAt {
        Objects.requireNonNull(value, "ExpiresAt cannot be null");

        if (value.isBefore(Instant.now())) {
            throw new IllegalArgumentException("ExpiresAt cannot be in the past");
        }
    }
}
