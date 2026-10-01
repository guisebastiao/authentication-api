package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AccountActivationRepositoryPort {
    void save(List<AccountActivation> accountActivation);

    AccountActivation save(AccountActivation accountActivation);

    Optional<AccountActivation> findByActivationTokenHash(String activationTokenHash);

    int deleteAllExpired(Instant now);
}
