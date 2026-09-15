package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;
import java.util.Objects;

public record Timestamp(Instant value) {
    public Timestamp {
        Objects.requireNonNull(value, "Timestamp cannot be null");

        if (value.isAfter(Instant.now())) {
            throw new IllegalArgumentException("Timestamp cannot be in the future");
        }
    }
}
