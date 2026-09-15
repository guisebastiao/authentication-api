package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;
import java.util.Objects;

public record ResendAvailableAt(Instant value) {
    public ResendAvailableAt {
        Objects.requireNonNull(value, "ResendAvailableAt cannot be null");

        if (value.isBefore(Instant.now())) {
            throw new IllegalArgumentException("ResendAvailableAt cannot be in the past");
        }
    }
}
