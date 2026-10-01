package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.adapter.out.logger.ScheduleLoggerPayload;
import br.com.guisebastiao.authenticationapi.application.port.in.CleanupRecoverPasswordsUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.LoggerPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;

import java.time.Instant;

public class CleanupRecoverPasswordService implements CleanupRecoverPasswordsUseCase {
    private static final String JOB_NAME = "CLEANUP_RECOVER_PASSWORD";

    private final RecoverPasswordRepositoryPort recoverPasswordRepository;
    private final LoggerPort logger;

    public CleanupRecoverPasswordService(
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            LoggerPort logger
    ) {
        this.recoverPasswordRepository = recoverPasswordRepository;
        this.logger = logger;
    }

    @Override
    public void execute() {
        Instant now = Instant.now();

        int affectedItems = recoverPasswordRepository.deleteAllExpired(now);

        ScheduleLoggerPayload payload = new ScheduleLoggerPayload(
                Instant.now(),
                "Expired password recovery records cleanup completed successfully",
                JOB_NAME,
                affectedItems
        );

        logger.info(payload);
    }
}
