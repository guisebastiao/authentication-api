package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.GetAccountActivationUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountActivationRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountActivationExpiredException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountActivationNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountAlreadyActivatedException;
import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;

import java.time.Instant;

public class GetAccountActivationService implements GetAccountActivationUseCase {
    private final AccountActivationRepositoryPort accountActivationRepository;

    public GetAccountActivationService(
            AccountActivationRepositoryPort accountActivationRepository
    ) {
        this.accountActivationRepository = accountActivationRepository;
    }

    @Override
    public AccountActivationResult execute(String activationCode) {
        AccountActivation accountActivation = accountActivationRepository.findByActivationToken(activationCode)
                .orElseThrow(AccountActivationNotFoundException::new);

        validateAccountActivation(accountActivation);

        return new AccountActivationResult(
                accountActivation.getId(),
                activationCode,
                accountActivation.getExpiresAt(),
                accountActivation.getExpiresAt()
        );
    }

    private void validateAccountActivation(AccountActivation accountActivation) {
        if (accountActivation.getActivatedAt() != null) {
            throw new AccountAlreadyActivatedException();
        }

        if (!accountActivation.getExpiresAt().isAfter(Instant.now())) {
            throw new AccountActivationExpiredException();
        }
    }
}
