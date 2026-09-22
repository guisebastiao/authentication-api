package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.ResendRecoverPasswordEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SendRecoverPasswordEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RateLimiterPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.domain.exception.*;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ResendRecoverPasswordEmailService implements ResendRecoverPasswordEmailUseCase {
    private static final int RESEND_EMAIL_AVAILABLE_MINUTES = 1;

    private final SendRecoverPasswordEmailUseCase sendRecoverPasswordEmailUseCase;
    private final RecoverPasswordRepositoryPort recoverPasswordRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RateLimiterPort rateLimiter;
    private final SecureRandom secureRandom;

    public ResendRecoverPasswordEmailService(
            SendRecoverPasswordEmailUseCase sendRecoverPasswordEmailUseCase,
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter,
            SecureRandom secureRandom
    ) {
        this.sendRecoverPasswordEmailUseCase = sendRecoverPasswordEmailUseCase;
        this.recoverPasswordRepository = recoverPasswordRepository;
        this.passwordEncoder = passwordEncoder;
        this.secureRandom = secureRandom;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void execute(String email, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.RECOVER_PASSWORD_RESEND,
                RateLimitKey.ip(ipAddress)
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        RecoverPassword recoverPasswordExists = recoverPasswordRepository.findActiveByAccountEmail(email)
                .orElseThrow(RecoverPasswordNotFoundException::new);

        validateRecoverPassword(recoverPasswordExists);

        String otpCode = String.format("%06d", secureRandom.nextInt(1_000_000));
        String otpCodeHash = passwordEncoder.hash(otpCode);

        Instant resendAvailableAt = Instant.now().plus(RESEND_EMAIL_AVAILABLE_MINUTES, ChronoUnit.MINUTES);

        recoverPasswordExists.setResendAvailableAt(resendAvailableAt);
        recoverPasswordExists.setOtpCodeHash(otpCodeHash);

        RecoverPassword recoverPassword = recoverPasswordRepository.save(recoverPasswordExists);

        sendRecoverPasswordEmailUseCase.execute(
                recoverPassword.getAccount().getEmail(),
                otpCode,
                recoverPassword.getExpiresAt()
        );
    }

    private void validateRecoverPassword(RecoverPassword recoverPassword) {
        if (!recoverPassword.getExpiresAt().isAfter(Instant.now())) {
            throw new RecoverPasswordExpiredException();
        }

        if (recoverPassword.getResendAvailableAt().isAfter(Instant.now())) {
            throw new RecoverPasswordResendNotAvailableException();
        }
    }
}
