package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.DisableAccountCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.DisableAccountUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SignOutAllUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountInvalidCredentialsException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

import java.time.Instant;

public class DisableAccountService implements DisableAccountUseCase {
    private final AccountRepositoryPort accountRepository;
    private final SignOutAllUseCase signOutAllUseCase;
    private final PasswordEncoderPort passwordEncoder;

    public DisableAccountService(
            AccountRepositoryPort accountRepository,
            SignOutAllUseCase signOutAllUseCase,
            PasswordEncoderPort passwordEncoder
    ) {
        this.accountRepository = accountRepository;
        this.signOutAllUseCase = signOutAllUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(Account account, DisableAccountCommand command) {
        boolean isMatch = passwordEncoder.matches(command.password(), account.getPasswordHash());

        if (!isMatch) {
            throw new AccountInvalidCredentialsException();
        }

        account.setDisabledAt(Instant.now());

        accountRepository.save(account);

        signOutAllUseCase.execute(account);
    }
}
