package br.com.guisebastiao.authenticationapi.adapter.out.logger;

public sealed interface LoggerPayload permits ErrorLoggerPayload, WarnLoggerPayload, InfoLoggerPayload, ScheduleLoggerPayload {
    String message();
}
