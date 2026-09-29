package br.com.guisebastiao.authenticationapi.adapter.in.dto.response;

import br.com.guisebastiao.authenticationapi.domain.enums.DeviceType;

import java.time.Instant;
import java.util.UUID;

public record SessionResponse(
        UUID id,
        DeviceType type,
        Instant lastSeenAt,
        String location,
        boolean isCurrent,
        Instant createdAt
) {
}
