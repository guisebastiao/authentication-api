package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;

public record ActivatedAt(Instant value) {
    public ActivatedAt {
        if (value.isBefore(Instant.now())) {
            throw new IllegalArgumentException("ActivatedAt cannot be in the past");
        }
    }
}
