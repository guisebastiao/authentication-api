package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.adapter.out.logger.LoggerPayload;

public interface LoggerPort {
    void warn(LoggerPayload payload);

    void error(LoggerPayload payload);
}
