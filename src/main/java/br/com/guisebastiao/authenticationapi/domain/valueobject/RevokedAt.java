package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;
import java.util.Objects;

public record RevokedAt(Instant value) {
    public RevokedAt {
        Objects.requireNonNull(value, "RevokedAt cannot be null");

        if (value.isAfter(Instant.now())) {
            throw new IllegalArgumentException("RevokedAt cannot be in the future");
        }
    }
}
