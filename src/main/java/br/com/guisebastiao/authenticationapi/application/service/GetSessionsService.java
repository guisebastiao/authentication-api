package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.result.PageResult;
import br.com.guisebastiao.authenticationapi.application.command.PageQueryCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.GetSessionsUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureHasherPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SessionRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import br.com.guisebastiao.authenticationapi.domain.exception.SessionNotFoundException;
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
    public PageResult<SessionResult> execute(Account account, String currentSession, PageQueryCommand paging) {
        if (currentSession.isBlank()) {
            throw new SessionNotFoundException();
        }

        PageResult<Session> sessions = sessionRepository.findAllByAccountIdAndNotRevoked(account.getId(), paging);

        String sessionTokenHash = secureHasher.hash(currentSession);

        List<SessionResult> content = sessions.content()
                .stream()
                .map((session) -> new SessionResult(
                        session.getId(),
                        session.getType(),
                        session.getLastSeenAt(),
                        session.getLocation(),
                        sessionTokenHash.equals(session.getSessionTokenHash()),
                        session.getCreatedAt()
                ))
                .toList();

        return new PageResult<>(
                content,
                sessions.totalItems(),
                sessions.totalPages(),
                sessions.page(),
                sessions.size()
        );
    }
}
