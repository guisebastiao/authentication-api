package br.com.guisebastiao.authenticationapi.adapter.out.logger;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

import java.time.Instant;

public record ErrorLoggerPayload(
        Instant timestamp,
        String message,
        String method,
        String path,
        int status,
        String ip,
        DomainErrorCode errorCode,
        Throwable exception,
        String userId,
        String sessionToken
) implements LoggerPayload {
}
