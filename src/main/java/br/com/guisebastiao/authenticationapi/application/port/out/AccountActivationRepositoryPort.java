package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountActivationRepositoryPort {
    void save(List<AccountActivation> accountActivation);
    AccountActivation save(AccountActivation accountActivation);
    Optional<AccountActivation> findByActivationToken(String activationToken);
    List<AccountActivation> findAllByAccountIdAndNotExpired(UUID accountId);
}
