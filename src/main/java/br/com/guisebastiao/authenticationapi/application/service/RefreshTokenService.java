package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.RefreshTokenCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateRefreshUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.RefreshTokenUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.ValidateSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.*;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import br.com.guisebastiao.authenticationapi.application.result.CreateRefreshResult;
import br.com.guisebastiao.authenticationapi.application.result.JwtValidationResult;
import br.com.guisebastiao.authenticationapi.domain.enums.JwtValidationStatus;
import br.com.guisebastiao.authenticationapi.domain.exception.*;
import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.Set;

public class RefreshTokenService implements RefreshTokenUseCase {
    private final ValidateSessionUseCase validateSessionUseCase;
    private final CreateRefreshUseCase createRefreshUseCase;
    private final RefreshRepositoryPort refreshRepository;
    private final SessionRepositoryPort sessionRepository;
    private final SecureHasherPort secureHasher;
    private final JwtTokenPort jwtToken;

    public RefreshTokenService(
            ValidateSessionUseCase validateSessionUseCase,
            CreateRefreshUseCase createRefreshUseCase,
            RefreshRepositoryPort refreshRepository,
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher,
            JwtTokenPort jwtToken
    ) {
        this.validateSessionUseCase = validateSessionUseCase;
        this.createRefreshUseCase = createRefreshUseCase;
        this.refreshRepository = refreshRepository;
        this.sessionRepository = sessionRepository;
        this.secureHasher = secureHasher;
        this.jwtToken = jwtToken;
    }

    @Override
    public AuthResult execute(RefreshTokenCommand command) {
        Session session = validateSessionUseCase.execute(command.sessionToken());

        JwtValidationResult jwtResult = jwtToken.validate(
                command.accessToken(),
                command.sessionToken()
        );

        boolean jwtIsExpired = validateJwtStatus(jwtResult.status());

        if (!jwtIsExpired) {
            return new AuthResult(command.accessToken(), command.refreshToken(), command.sessionToken());
        }

        Refresh previousRefresh = consumeAndValidateRefresh(command.refreshToken(), session);

        CreateRefreshResult refreshResult = createRefreshUseCase.execute(session);

        previousRefresh.setReplacedBy(refreshResult.refresh());

        refreshRepository.save(previousRefresh);

        updateSession(session);

        String accessToken = jwtToken.generate(session.getAccount(), command.sessionToken());

        return new AuthResult(accessToken, refreshResult.refreshToken(), command.sessionToken());
    }

    private void updateSession(Session session) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(7, ChronoUnit.DAYS);

        session.setLastSeenAt(now);
        session.setExpiresAt(expiresAt);

        sessionRepository.save(session);
    }

    private boolean validateJwtStatus(JwtValidationStatus status) {
        Set<JwtValidationStatus> allowedStatuses = EnumSet.of(
                JwtValidationStatus.VALID,
                JwtValidationStatus.EXPIRED
        );

        if (!allowedStatuses.contains(status)) {
            throw new UnauthorizedException();
        }

        return status == JwtValidationStatus.EXPIRED;
    }

    private Refresh consumeAndValidateRefresh(String refreshToken, Session session) {
        String refreshTokenHash = secureHasher.hash(refreshToken);

        Refresh refresh = refreshRepository.findByRefreshTokenHash(refreshTokenHash)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (refresh.getRevokedAt() != null) {
            throw new RefreshTokenRevokedException();
        }

        if (!refresh.getSession().getId().equals(session.getId())) {
            throw new RefreshTokenInvalidException();
        }

        refresh.setRevokedAt(Instant.now());

        return refresh;
    }
}
