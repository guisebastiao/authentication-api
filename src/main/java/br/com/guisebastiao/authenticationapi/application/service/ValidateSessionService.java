package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.ValidateSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.*;
import br.com.guisebastiao.authenticationapi.domain.exception.*;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.time.Instant;

public class ValidateSessionService implements ValidateSessionUseCase {
    private final SessionRepositoryPort sessionRepository;
    private final SecureHasherPort secureHasher;

    public ValidateSessionService(
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher
    ) {
        this.sessionRepository = sessionRepository;
        this.secureHasher = secureHasher;
    }

    @Override
    public Session execute(String sessionToken) {
        String sessionTokenHash = secureHasher.hash(sessionToken);

        Session session = sessionRepository.findBySessionTokenHash(sessionTokenHash)
                .orElseThrow(SessionNotFoundException::new);

        if (session.getRevokedAt() != null) {
            throw new SessionRevokedException();
        }

        if (!session.getExpiresAt().isAfter(Instant.now())) {
            throw new SessionExpiredException();
        }

        return session;
    }
}
