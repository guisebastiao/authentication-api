package br.com.guisebastiao.authenticationapi.adapter.out.logger;

import br.com.guisebastiao.authenticationapi.application.port.out.LoggerPort;
import br.com.guisebastiao.authenticationapi.infrastructure.config.LoggerConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Slf4jLoggerAdapter implements LoggerPort {
    private final Logger logger;

    public Slf4jLoggerAdapter(
            @Qualifier(LoggerConfig.APPLICATION_LOGGER)
            Logger logger
    ) {
        this.logger = logger;
    }


    @Override
    public void warn(LoggerPayload payload) {
        logger.warn("""     
                ┌─ REQUEST WARNING ───────────────────────
                │ Method: {}
                │ Path: {}
                │ Status: {}
                │ IP: {}
                │ Error Code: {}
                │ User: {}
                │ Session: {}
                │ Message: {}
                │ Exception: {}
                └─────────────────────────────────────────
                """,
                payload.method(),
                payload.path(),
                payload.status(),
                payload.ip(),
                payload.errorCode(),
                payload.userId(),
                payload.sessionToken(),
                payload.message(),
                payload.exception()
        );
    }

    @Override
    public void error(LoggerPayload payload) {
        logger.error(
                """
                ┌─ REQUEST ERROR ─────────────────────────
                │ Method: {}
                │ Path: {}
                │ Status: {}
                │ IP: {}
                │ Error Code: {}
                │ User: {}
                │ Session: {}
                │ Message: {}
                │ Exception: {}
                └─────────────────────────────────────────
                """,
                payload.method(),
                payload.path(),
                payload.status(),
                payload.ip(),
                payload.errorCode(),
                payload.userId(),
                payload.sessionToken(),
                payload.message(),
                payload.exception()
        );
    }
}
