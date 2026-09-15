package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;
import java.util.Objects;

public record CreatedAt(Instant value) {
    public CreatedAt {
        Objects.requireNonNull(value, "CreatedAt cannot be null");

        if (value.isAfter(Instant.now())) {
            throw new IllegalArgumentException("CreatedAt cannot be in the future");
        }
    }
}
