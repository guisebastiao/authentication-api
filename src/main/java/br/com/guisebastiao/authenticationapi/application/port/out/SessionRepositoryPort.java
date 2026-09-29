package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.application.result.PageResult;
import br.com.guisebastiao.authenticationapi.application.command.PageQueryCommand;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepositoryPort {
    Session save(Session session);

    void save(List<Session> sessions);

    Optional<Session> findBySessionTokenHash(String sessionTokenHash);

    PageResult<Session> findAllByAccountIdAndNotRevoked(UUID accountId, PageQueryCommand pagination);

    List<Session> findAllByAccountIdAndNotRevoked(UUID accountId);

    Optional<Session> findByAccountIdAndSessionTokenHash(UUID userId, String sessionTokenHash);

    List<Session> findAllByAccountIdAndSessionIdsAndNotRevoked(UUID accountId, List<UUID> sessionIds);
}
