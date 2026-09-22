package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.SignOutCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.SignOutUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.RefreshRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureHasherPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SessionRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.exception.SessionExpiredException;
import br.com.guisebastiao.authenticationapi.domain.exception.SessionNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.exception.SessionRevokedException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.time.Instant;
import java.util.List;

public class SignOutService implements SignOutUseCase {
    private final SessionRepositoryPort sessionRepository;
    private final RefreshRepositoryPort refreshRepository;
    private final SecureHasherPort secureHasher;

    public SignOutService(
            SessionRepositoryPort sessionRepository,
            RefreshRepositoryPort refreshRepository,
            SecureHasherPort secureHasher
    ) {
        this.sessionRepository = sessionRepository;
        this.refreshRepository = refreshRepository;
        this.secureHasher = secureHasher;
    }

    @Override
    public void execute(Account account, SignOutCommand command) {
        consumeSignOut(account, command.sessionToken());
    }

    @Override
    public void execute(Account account, String sessionToken) {
        consumeSignOut(account, sessionToken);
    }

    private void consumeSignOut(Account account, String sessionToken) {
        String sessionTokenHash = secureHasher.hash(sessionToken);

        Session session = sessionRepository.findByUserIdAndSessionTokenHash(account.getId(), sessionTokenHash)
                .orElseThrow(SessionNotFoundException::new);

        validateSession(session);

        List<Refresh> refreshes = refreshRepository.findAllBySessionIdsAndNotRevoked(List.of(session.getId()));

        refreshes.forEach((refresh) -> refresh.setRevokedAt(Instant.now()));

        refreshRepository.save(refreshes);

        session.setRevokedAt(Instant.now());

        sessionRepository.save(session);
    }

    private void validateSession(Session session) {
        if (!session.getExpiresAt().isAfter(Instant.now())) {
            throw new SessionExpiredException();
        }

        if (session.getRevokedAt() != null) {
            throw new SessionRevokedException();
        }
    }
}
