package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.adapter.out.logger.ScheduleLoggerPayload;
import br.com.guisebastiao.authenticationapi.application.port.in.CleanupAccountActivationUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountActivationRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.LoggerPort;

import java.time.Instant;

public class CleanupAccountActivationService implements CleanupAccountActivationUseCase {
    private static final String JOB_NAME = "CLEANUP_ACCOUNT_ACTIVATION";

    private final AccountActivationRepositoryPort accountActivationRepository;
    private final LoggerPort logger;

    public CleanupAccountActivationService(
            AccountActivationRepositoryPort accountActivationRepository,
            LoggerPort logger
    ) {
        this.accountActivationRepository = accountActivationRepository;
        this.logger = logger;
    }

    @Override
    public void execute() {
        Instant now = Instant.now();

        int affectedItems = accountActivationRepository.deleteAllExpired(now);

        ScheduleLoggerPayload payload = new ScheduleLoggerPayload(
                Instant.now(),
                "Expired account activations cleanup completed successfully",
                JOB_NAME,
                affectedItems
        );

        logger.info(payload);
    }
}
