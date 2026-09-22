package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.ChangePasswordCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.ChangePasswordUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountInvalidCredentialsException;
import br.com.guisebastiao.authenticationapi.domain.exception.PasswordSameAsCurrentException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public class ChangePasswordService implements ChangePasswordUseCase {
    private final AccountRepositoryPort accountRepository;
    private final PasswordEncoderPort passwordEncoder;

    public ChangePasswordService(
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder
    ) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(Account account, ChangePasswordCommand command) {
        boolean isMatch = passwordEncoder.matches(command.currentPassword(), account.getPasswordHash());

        if (!isMatch) {
            throw new AccountInvalidCredentialsException();
        }

        boolean samePassword = passwordEncoder.matches(command.newPassword(), account.getPasswordHash());

        if (samePassword) {
            throw new PasswordSameAsCurrentException();
        }

        String newPasswordHash = passwordEncoder.hash(command.newPassword());

        account.setPasswordHash(newPasswordHash);

        accountRepository.save(account);
    }
}
