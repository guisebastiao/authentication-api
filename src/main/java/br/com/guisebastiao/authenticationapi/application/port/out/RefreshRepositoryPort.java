package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.Refresh;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshRepositoryPort {
    Refresh save(Refresh refresh);

    void save(List<Refresh> refresh);

    Optional<Refresh> findByRefreshTokenHash(String refreshTokenHash);

    List<Refresh> findAllBySessionIdsAndNotRevoked(List<UUID> sessionIds);
}
