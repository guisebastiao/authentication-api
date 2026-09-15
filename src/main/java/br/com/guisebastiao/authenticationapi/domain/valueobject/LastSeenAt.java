package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;
import java.util.Objects;

public record LastSeenAt(Instant value) {
    public LastSeenAt {
        Objects.requireNonNull(value, "LastSeenAt cannot be null");

        if (value.isAfter(Instant.now())) {
            throw new IllegalArgumentException("LastSeenAt cannot be in the future");
        }
    }
}
