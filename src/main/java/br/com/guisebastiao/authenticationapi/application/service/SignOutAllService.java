package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.SignOutAllUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.RefreshRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SessionRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class SignOutAllService implements SignOutAllUseCase {
    private final SessionRepositoryPort sessionRepository;
    private final RefreshRepositoryPort refreshRepository;

    public SignOutAllService(
            SessionRepositoryPort sessionRepository,
            RefreshRepositoryPort refreshRepository
    ) {
        this.sessionRepository = sessionRepository;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public void execute(Account account) {
        Instant revokedAt = Instant.now();

        List<Session> sessions = sessionRepository.findAllByUserIdAndNotRevoked(account.getId());

        if (sessions.isEmpty()) {
            return;
        }

        List<UUID> sessionIds = sessions.stream()
                .map(Session::getId)
                .toList();

        List<Refresh> refreshes = refreshRepository.findAllBySessionIdsAndNotRevoked(sessionIds);

        refreshes.forEach(refresh -> refresh.setRevokedAt(revokedAt));

        sessions.forEach(session -> session.setRevokedAt(revokedAt));

        if (!refreshes.isEmpty()) {
            refreshRepository.save(refreshes);
        }

        sessionRepository.save(sessions);
    }
}
