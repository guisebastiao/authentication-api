package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.ResendAccountActivationEmailCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.ResendAccountActivationEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SendAccountActivationEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountActivationRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RateLimiterPort;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountActivationExpiredException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountActivationNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountActivationResendNotAvailableException;
import br.com.guisebastiao.authenticationapi.domain.exception.RateLimitExceededException;
import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ResendAccountActivationEmailService implements ResendAccountActivationEmailUseCase {
    private static final int RESEND_EMAIL_AVAILABLE_MINUTES = 1;

    private final SendAccountActivationEmailUseCase sendAccountActivationEmailUseCase;
    private final AccountActivationRepositoryPort accountActivationRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RateLimiterPort rateLimiter;
    private final SecureRandom secureRandom;

    public ResendAccountActivationEmailService(
            SendAccountActivationEmailUseCase sendAccountActivationEmailUseCase,
            AccountActivationRepositoryPort accountActivationRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter,
            SecureRandom secureRandom
    ) {
        this.sendAccountActivationEmailUseCase = sendAccountActivationEmailUseCase;
        this.accountActivationRepository = accountActivationRepository;
        this.passwordEncoder = passwordEncoder;
        this.secureRandom = secureRandom;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public AccountActivationResult execute(ResendAccountActivationEmailCommand command, String ipAddress) {
        return executeResendEmail(command.activationToken(), ipAddress);
    }

    @Override
    public AccountActivationResult execute(String activationToken, String ipAddress) {
        return executeResendEmail(activationToken, ipAddress);
    }

    private AccountActivationResult executeResendEmail(String activationToken, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.ACTIVATE_ACCOUNT_RESEND,
                RateLimitKey.ip(ipAddress)
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        AccountActivation accountActivationExists = accountActivationRepository.findByActivationToken(activationToken)
                .orElseThrow(AccountActivationNotFoundException::new);

        validateAccountActivation(accountActivationExists);

        String otpCode = String.format("%06d", secureRandom.nextInt(1_000_000));
        String otpCodeHash = passwordEncoder.hash(otpCode);

        Instant resendAvailableAt = Instant.now().plus(RESEND_EMAIL_AVAILABLE_MINUTES, ChronoUnit.MINUTES);

        accountActivationExists.setResendAvailableAt(resendAvailableAt);
        accountActivationExists.setOtpCodeHash(otpCodeHash);

        AccountActivation accountActivation = accountActivationRepository.save(accountActivationExists);

        sendAccountActivationEmailUseCase.execute(
                accountActivation.getAccount().getEmail(),
                otpCode,
                accountActivation.getExpiresAt()
        );

        return new AccountActivationResult(
                accountActivation.getId(),
                activationToken,
                accountActivation.getExpiresAt(),
                resendAvailableAt
        );
    }

    private void validateAccountActivation(AccountActivation accountActivation) {
        if (!accountActivation.getExpiresAt().isAfter(Instant.now())) {
            throw new AccountActivationExpiredException();
        }

        if (!accountActivation.getResendAvailableAt().isAfter(Instant.now())) {
            throw new AccountActivationResendNotAvailableException();
        }
    }
}
