package br.com.guisebastiao.authenticationapi.adapter.in.scheduler;

import br.com.guisebastiao.authenticationapi.application.port.in.CleanupRecoverPasswordsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoverPasswordCleanupScheduler {
    private final CleanupRecoverPasswordsUseCase cleanupRecoverPasswordsUseCase;

    @Scheduled(fixedDelayString = "${app.scheduler.recover-password-cleanup-rate}")
    public void cleanup() {
        cleanupRecoverPasswordsUseCase.execute();
    }
}
