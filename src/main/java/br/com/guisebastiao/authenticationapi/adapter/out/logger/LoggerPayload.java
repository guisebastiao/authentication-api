package br.com.guisebastiao.authenticationapi.adapter.out.logger;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

import java.time.Instant;
import java.util.UUID;

public record LoggerPayload(
        Instant timestamp,
        String message,
        String method,
        String path,
        int status,
        String ip,
        DomainErrorCode errorCode,
        StackTraceElement[] exception,
        String userId,
        String sessionToken
) {
}
