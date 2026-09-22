package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.CreateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateRecoverPasswordUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SendRecoverPasswordEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RateLimiterPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.exception.RateLimitExceededException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class CreateRecoverPasswordService implements CreateRecoverPasswordUseCase {
    private static final int RECOVER_PASSWORD_EXPIRES_MINUTES = 15;
    private static final int RESEND_EMAIL_AVAILABLE_MINUTES = 1;

    private final ResendRecoverPasswordEmailService resendRecoverPasswordEmailService;
    private final SendRecoverPasswordEmailUseCase sendRecoverPasswordEmailUseCase;
    private final RecoverPasswordRepositoryPort recoverPasswordRepository;
    private final AccountRepositoryPort accountRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RateLimiterPort rateLimiter;
    private final SecureRandom secureRandom;

    public CreateRecoverPasswordService(
            ResendRecoverPasswordEmailService resendRecoverPasswordEmailService,
            SendRecoverPasswordEmailUseCase sendRecoverPasswordEmailUseCase,
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter,
            SecureRandom secureRandom
    ) {
        this.resendRecoverPasswordEmailService = resendRecoverPasswordEmailService;
        this.sendRecoverPasswordEmailUseCase = sendRecoverPasswordEmailUseCase;
        this.recoverPasswordRepository = recoverPasswordRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.rateLimiter = rateLimiter;
        this.secureRandom = secureRandom;
    }

    @Override
    public void execute(CreateRecoverPasswordCommand command, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.RECOVER_PASSWORD_CREATE,
                RateLimitKey.ip(ipAddress),
                RateLimitKey.email(command.email())
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        Optional<RecoverPassword> existingRecoverPassword = recoverPasswordRepository
                .findActiveByAccountEmail(command.email());


        if (existingRecoverPassword.isPresent()) {
            resendRecoverPasswordEmailService.execute(command.email(), ipAddress);

            return;
        }


        String otpCode = String.format("%06d", secureRandom.nextInt(1_000_000));

        Account account = accountRepository.findByEmail(command.email())
                .orElseThrow(AccountNotFoundException::new);

        RecoverPassword recoverPassword = createRecoverPassword(account, otpCode);

        sendRecoverPasswordEmailUseCase.execute(command.email(), otpCode, recoverPassword.getExpiresAt());

        recoverPasswordRepository.save(recoverPassword);
    }

    private RecoverPassword createRecoverPassword(Account account, String otpCode) {
        Instant expiresAt = Instant.now().plus(RECOVER_PASSWORD_EXPIRES_MINUTES, ChronoUnit.MINUTES);
        Instant resendAvailableAt = Instant.now().plus(RESEND_EMAIL_AVAILABLE_MINUTES, ChronoUnit.MINUTES);

        String otpCodeHash = passwordEncoder.hash(otpCode);

        RecoverPassword recoverPasswordEntity = new RecoverPassword();
        recoverPasswordEntity.setAccount(account);
        recoverPasswordEntity.setOtpCodeHash(otpCodeHash);
        recoverPasswordEntity.setExpiresAt(expiresAt);
        recoverPasswordEntity.setResendAvailableAt(resendAvailableAt);

        return recoverPasswordEntity;
    }
}
