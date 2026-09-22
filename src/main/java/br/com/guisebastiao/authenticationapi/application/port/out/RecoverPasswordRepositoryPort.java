package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.util.Optional;

public interface RecoverPasswordRepositoryPort {
    RecoverPassword save(RecoverPassword recoverPassword);

    Optional<RecoverPassword> findByRecoverToken(String recoverToken);

    Optional<RecoverPassword> findActiveByAccountEmail(String email);
}
