package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.GetSessionsUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureHasherPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SessionRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.util.List;

public class GetSessionsService implements GetSessionsUseCase {
    private final SessionRepositoryPort sessionRepository;
    private final SecureHasherPort secureHasher;

    public GetSessionsService(
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher
    ) {
        this.sessionRepository = sessionRepository;
        this.secureHasher = secureHasher;
    }

    @Override
    public List<SessionResult> execute(Account account, String currentSession) {
        List<Session> sessions = sessionRepository.findAllByUserIdAndNotRevoked(account.getId());

        String sessionTokenHash = secureHasher.hash(currentSession);

        return sessions.stream()
                .map((session) -> new SessionResult(
                        session.getId(),
                        session.getType(),
                        session.getLastSeenAt(),
                        session.getLocation(),
                        sessionTokenHash.equals(session.getSessionTokenHash()),
                        session.getCreatedAt()
                ))
                .toList();
    }
}
