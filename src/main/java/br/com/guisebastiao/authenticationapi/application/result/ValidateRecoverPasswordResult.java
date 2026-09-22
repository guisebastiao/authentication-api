package br.com.guisebastiao.authenticationapi.application.result;

import java.time.Instant;
import java.util.UUID;

public record ValidateRecoverPasswordResult(
        UUID id,
        String recoverToken,
        Instant expiresAt,
        Instant resendAvailableAt
) {
}
