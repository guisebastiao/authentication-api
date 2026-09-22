package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.AccountActivateCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.AccountActivateUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountActivationRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RateLimiterPort;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.domain.enums.AccountStatus;
import br.com.guisebastiao.authenticationapi.domain.exception.*;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;

import java.time.Instant;

public class AccountActivateService implements AccountActivateUseCase {
    private final AccountActivationRepositoryPort accountActivationRepository;
    private final AccountRepositoryPort accountRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RateLimiterPort rateLimiter;

    public AccountActivateService(
            AccountActivationRepositoryPort accountActivationRepository,
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter
    ) {
        this.accountActivationRepository = accountActivationRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void execute(AccountActivateCommand command, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.ACTIVATE_ACCOUNT,
                RateLimitKey.ip(ipAddress)
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        AccountActivation accountActivation = accountActivationRepository
                .findByActivationToken(command.activationToken())
                .orElseThrow(AccountActivationNotFoundException::new);

        validateAccountActivation(accountActivation);

        boolean otpIsValid = passwordEncoder.matches(command.otpCode(), accountActivation.getOtpCodeHash());

        if (!otpIsValid) {
            throw new IncorrectOtpException();
        }

        activateAccount(accountActivation);
    }

    private void activateAccount(AccountActivation accountActivation) {
        Account account = accountActivation.getAccount();
        account.setStatus(AccountStatus.ACTIVATED);

        accountActivation.setActivatedAt(Instant.now());

        accountRepository.save(account);
        accountActivationRepository.save(accountActivation);
    }

    private void validateAccountActivation(AccountActivation accountActivation) {
        if (accountActivation.getActivatedAt() != null) {
            throw new AccountAlreadyActivatedException();
        }

        if (!accountActivation.getExpiresAt().isAfter(Instant.now())) {
            throw new AccountActivationExpiredException();
        }
    }
}
