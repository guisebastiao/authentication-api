package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.ValidateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.ValidateRecoverPasswordUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RateLimiterPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureRandomGeneratorPort;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.application.result.ValidateRecoverPasswordResult;
import br.com.guisebastiao.authenticationapi.domain.exception.*;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.time.Instant;
import java.util.Optional;

public class ValidateRecoverPasswordService implements ValidateRecoverPasswordUseCase {
    private static final int RECOVER_TOKEN_SIZE = 32;

    private final RecoverPasswordRepositoryPort recoverPasswordRepository;
    private final SecureRandomGeneratorPort secureRandomGenerator;
    private final PasswordEncoderPort passwordEncoder;
    private final RateLimiterPort rateLimiter;

    public ValidateRecoverPasswordService(
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            SecureRandomGeneratorPort secureRandomGenerator,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter
    ) {
        this.recoverPasswordRepository = recoverPasswordRepository;
        this.secureRandomGenerator = secureRandomGenerator;
        this.passwordEncoder = passwordEncoder;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public ValidateRecoverPasswordResult execute(ValidateRecoverPasswordCommand command, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.RECOVER_PASSWORD_VALIDATE,
                RateLimitKey.ip(ipAddress),
                RateLimitKey.email(command.email())
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        Optional<RecoverPassword> recoverPasswordExisting = recoverPasswordRepository
                .findActiveByAccountEmail(command.email());

        if (recoverPasswordExisting.isEmpty()) {
            throw new RecoverPasswordNotFoundException();
        }

        RecoverPassword recoverPassword = recoverPasswordExisting.get();

        validateRecoverPassword(recoverPassword);

        boolean otpIsValid = passwordEncoder.matches(command.otpCode(), recoverPassword.getOtpCodeHash());

        if (!otpIsValid) {
            throw new IncorrectOtpException();
        }

        String recoverToken = secureRandomGenerator.generate(RECOVER_TOKEN_SIZE);

        recoverPassword.setRecoverToken(recoverToken);
        recoverPassword.setVerifiedAt(Instant.now());

        recoverPasswordRepository.save(recoverPassword);

        return new ValidateRecoverPasswordResult(
                recoverPassword.getId(),
                recoverPassword.getRecoverToken(),
                recoverPassword.getExpiresAt(),
                recoverPassword.getResendAvailableAt()
        );
    }

    private void validateRecoverPassword(RecoverPassword recoverPassword) {
        if (!recoverPassword.getExpiresAt().isAfter(Instant.now())) {
            throw new RecoverPasswordExpiredException();
        }

        if (recoverPassword.getUsedAt() != null) {
            throw new RecoverPasswordAlreadyUsedException();
        }
    }
}
