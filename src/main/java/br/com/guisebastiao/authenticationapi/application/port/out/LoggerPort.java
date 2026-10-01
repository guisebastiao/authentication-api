package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.adapter.out.logger.ErrorLoggerPayload;
import br.com.guisebastiao.authenticationapi.adapter.out.logger.WarnLoggerPayload;
import br.com.guisebastiao.authenticationapi.adapter.out.logger.LoggerPayload;

public interface LoggerPort {
    void info(LoggerPayload payload);

    void warn(WarnLoggerPayload payload);

    void error(ErrorLoggerPayload payload);
}
