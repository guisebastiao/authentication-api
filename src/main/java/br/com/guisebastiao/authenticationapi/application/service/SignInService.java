package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.SignInCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateRefreshUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SignInUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AuthenticationPort;
import br.com.guisebastiao.authenticationapi.application.port.out.JwtTokenPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RateLimiterPort;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;
import br.com.guisebastiao.authenticationapi.domain.enums.AccountStatus;
import br.com.guisebastiao.authenticationapi.domain.exception.RateLimitExceededException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

import java.util.Set;
import java.util.stream.Collectors;

public class SignInService implements SignInUseCase {
    private final CreateSessionUseCase createSessionUseCase;
    private final CreateRefreshUseCase createRefreshUseCase;
    private final AuthenticationPort authenticationPort;
    private final RateLimiterPort rateLimiter;
    private final JwtTokenPort jwtToken;

    public SignInService(
            CreateSessionUseCase createSessionUseCase,
            CreateRefreshUseCase createRefreshUseCase,
            AuthenticationPort authenticationPort,
            RateLimiterPort rateLimiter,
            JwtTokenPort jwtToken
    ) {
        this.createSessionUseCase = createSessionUseCase;
        this.createRefreshUseCase = createRefreshUseCase;
        this.authenticationPort = authenticationPort;
        this.rateLimiter = rateLimiter;
        this.jwtToken = jwtToken;
    }

    @Override
    public AuthResult execute(SignInCommand command, String userAgent, String ipAddress) {
        RateLimitResult rateLimit = rateLimiter.consume(
                RateLimitPolicy.SIGN_IN,
                RateLimitKey.ip(ipAddress),
                RateLimitKey.email(command.email())
        );

        if (!rateLimit.allowed()) {
            throw new RateLimitExceededException(rateLimit.retryAfterSeconds());
        }

        Account account = authenticationPort.authenticate(command.email(), command.password());

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