package br.com.guisebastiao.authenticationapi.application.result;

import java.time.Instant;
import java.util.UUID;

public record AccountActivationResult(
        UUID id,
        String activationToken,
        Instant expiresAt,
        Instant resendAvailableAt
) {
}
