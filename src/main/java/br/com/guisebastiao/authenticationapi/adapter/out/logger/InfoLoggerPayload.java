package br.com.guisebastiao.authenticationapi.adapter.out.logger;

import java.time.Instant;

public record InfoLoggerPayload(
        Instant timestamp,
        String message,
        String operation,
        String userId
) implements LoggerPayload {
}