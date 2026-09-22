package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.GetCurrentSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureHasherPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SessionRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import br.com.guisebastiao.authenticationapi.domain.exception.SessionNotBelongToAccountException;
import br.com.guisebastiao.authenticationapi.domain.exception.SessionNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

public class GetCurrentSessionService implements GetCurrentSessionUseCase {
    private final SessionRepositoryPort sessionRepository;
    private final SecureHasherPort secureHasher;

    public GetCurrentSessionService(
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher
    ) {
        this.sessionRepository = sessionRepository;
        this.secureHasher = secureHasher;
    }

    @Override
    public SessionResult execute(Account account, String currentSession) {
        String sessionTokenHash = secureHasher.hash(currentSession);

        Session session = sessionRepository.findBySessionTokenHash(sessionTokenHash)
                .orElseThrow(SessionNotFoundException::new);

        if (!session.getAccount().getId().equals(account.getId())) {
            throw new SessionNotBelongToAccountException();
        }

        return new SessionResult(
                session.getId(),
                session.getType(),
                session.getLastSeenAt(),
                session.getLocation(),
                true,
                session.getCreatedAt()
        );
    }
}
