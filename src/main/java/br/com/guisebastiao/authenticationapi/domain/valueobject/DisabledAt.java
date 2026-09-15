package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.time.Instant;

public record DisabledAt(Instant value) {
    public DisabledAt {
        if (value.isBefore(Instant.now())) {
            throw new IllegalArgumentException("DisabledAt cannot be in the past");
        }
    }
}
