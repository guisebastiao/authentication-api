package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.GoogleSignInCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateRefreshUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.GoogleSignUpUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.*;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import br.com.guisebastiao.authenticationapi.application.result.GoogleAuthorizationResult;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.domain.enums.AccountStatus;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountDisabledException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountNotActivatedException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.exception.RateLimitExceededException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public class GoogleSignUpService implements GoogleSignUpUseCase {
    private final GoogleAuthorizationPort googleAuthorization;
    private final CreateSessionUseCase createSessionUseCase;
    private final CreateRefreshUseCase createRefreshUseCase;
    private final AccountRepositoryPort accountRepository;
    private final RateLimiterPort rateLimiter;
    private final JwtTokenPort jwtToken;

    public GoogleSignUpService(
            GoogleAuthorizationPort googleAuthorization,
            CreateSessionUseCase createSessionUseCase,
            CreateRefreshUseCase createRefreshUseCase,
            AccountRepositoryPort accountRepository,
            RateLimiterPort rateLimiter,
            JwtTokenPort jwtToken
    ) {
        this.createSessionUseCase = createSessionUseCase;
        this.createRefreshUseCase = createRefreshUseCase;
        this.googleAuthorization = googleAuthorization;
        this.accountRepository = accountRepository;
        this.rateLimiter = rateLimiter;
        this.jwtToken = jwtToken;
    }

    @Override
    public AuthResult execute(GoogleSignInCommand command, String userAgent, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.GOOGLE_SIGN_IN,
                RateLimitKey.ip(ipAddress)
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        GoogleAuthorizationResult googleResult = googleAuthorization.authorize(command.credential());

        Account account = accountRepository.findByEmail(googleResult.email())
                .orElseThrow(AccountNotFoundException::new);

        if (account.getStatus() == AccountStatus.PENDING) {
            throw new AccountNotActivatedException();
        }

        if (account.getStatus() == AccountStatus.DISABLED) {
            throw new AccountDisabledException();
        }

        CreateSessionUseCase.CreateSessionResult sessionResult = createSessionUseCase.execute(
                account,
                userAgent,
                ipAddress
        );

        CreateRefreshUseCase.CreateRefreshResult refreshResult = createRefreshUseCase.execute(sessionResult.session());

        String accessToken = jwtToken.generate(account, refreshResult.refreshToken(), sessionResult.sessionToken());

        return new AuthResult(accessToken, refreshResult.refreshToken(), sessionResult.sessionToken());
    }
}
