package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.time.Instant;
import java.util.Optional;

public interface RecoverPasswordRepositoryPort {
    RecoverPassword save(RecoverPassword recoverPassword);

    Optional<RecoverPassword> findByRecoverTokenHash(String recoverTokenHash);

    Optional<RecoverPassword> findByRecoverTokenHashAndNotVerified(String recoverTokenHash);

    int deleteAllExpired(Instant now);
}
