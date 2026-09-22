package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.SessionSignOutUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.RefreshRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SessionRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class SessionSignOutService implements SessionSignOutUseCase {
    private final SessionRepositoryPort sessionRepository;
    private final RefreshRepositoryPort refreshRepository;

    public SessionSignOutService(
            SessionRepositoryPort sessionRepository,
            RefreshRepositoryPort refreshRepository
    ) {
        this.sessionRepository = sessionRepository;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public void execute(Account account, List<UUID> sessionsIds) {
        List<Session> sessions = sessionRepository.findAllByUserIdAndSessionIdsAndNotRevoked(
                account.getId(),
                sessionsIds
        );

        if (sessions.isEmpty()) {
            return;
        }

        List<UUID> authorizedSessionIds = sessions.stream()
                .map(Session::getId)
                .toList();

        List<Refresh> refreshes = refreshRepository.findAllBySessionIdsAndNotRevoked(authorizedSessionIds);

        Instant revokedAt = Instant.now();

        refreshes.forEach((refresh) -> refresh.setRevokedAt(revokedAt));
        sessions.forEach((session) -> session.setRevokedAt(revokedAt));

        refreshRepository.save(refreshes);
        sessionRepository.save(sessions);
    }
}
