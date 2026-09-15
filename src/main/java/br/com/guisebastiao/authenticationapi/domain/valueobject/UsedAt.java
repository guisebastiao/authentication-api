package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;

public record UsedAt(Instant value) {
    public UsedAt {
        if (value.isBefore(Instant.now())) {
            throw new IllegalArgumentException("UsedAt cannot be in the past");
        }
    }
}
