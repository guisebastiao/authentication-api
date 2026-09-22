package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepositoryPort {
    Session save(Session session);

    void save(List<Session> sessions);

    Optional<Session> findBySessionTokenHash(String sessionTokenHash);

    List<Session> findAllByUserIdAndNotRevoked(UUID userId);

    Optional<Session> findByUserIdAndSessionTokenHash(UUID userId, String sessionTokenHash);

    List<Session> findAllByUserIdAndSessionIdsAndNotRevoked(UUID userId, List<UUID> sessionIds);
}
