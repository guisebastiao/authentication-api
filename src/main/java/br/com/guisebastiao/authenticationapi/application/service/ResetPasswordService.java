package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.ResetPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.ResetPasswordUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SignOutAllUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.exception.RecoverPasswordAlreadyUsedException;
import br.com.guisebastiao.authenticationapi.domain.exception.RecoverPasswordExpiredException;
import br.com.guisebastiao.authenticationapi.domain.exception.RecoverPasswordInvalidException;
import br.com.guisebastiao.authenticationapi.domain.exception.RecoverPasswordNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.time.Instant;

public class ResetPasswordService implements ResetPasswordUseCase {
    private final RecoverPasswordRepositoryPort recoverPasswordRepository;
    private final AccountRepositoryPort accountRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final SignOutAllUseCase signOutAllUseCase;

    public ResetPasswordService(
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            AccountRepositoryPort accountRepository,
            SignOutAllUseCase signOutAllUseCase,
            PasswordEncoderPort passwordEncoder
    ) {
        this.recoverPasswordRepository = recoverPasswordRepository;
        this.accountRepository = accountRepository;
        this.signOutAllUseCase = signOutAllUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(ResetPasswordCommand command) {
        RecoverPassword recoverPassword = recoverPasswordRepository.findByRecoverToken(command.recoverToken())
                .orElseThrow(RecoverPasswordNotFoundException::new);

        validateRecoverPassword(recoverPassword);

        Account account = recoverPassword.getAccount();

        String newPasswordHash = passwordEncoder.hash(command.newPassword());

        account.setPasswordHash(newPasswordHash);

        accountRepository.save(account);

        recoverPassword.setUsedAt(Instant.now());

        signOutAllUseCase.execute(account);

        recoverPasswordRepository.save(recoverPassword);
    }

    private void validateRecoverPassword(RecoverPassword recoverPassword) {
        if (!recoverPassword.getExpiresAt().isAfter(Instant.now())) {
            throw new RecoverPasswordExpiredException();
        }

        if (recoverPassword.getUsedAt() != null) {
            throw new RecoverPasswordAlreadyUsedException();
        }

        if (recoverPassword.getVerifiedAt() == null) {
            throw new RecoverPasswordInvalidException();
        }
    }
}
