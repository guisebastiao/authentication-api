package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.ResetPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.ResetPasswordUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SignOutAllUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.PasswordEncoderPort;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureHasherPort;
import br.com.guisebastiao.authenticationapi.domain.exception.*;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;

import java.time.Instant;

public class ResetPasswordService implements ResetPasswordUseCase {
    private final RecoverPasswordRepositoryPort recoverPasswordRepository;
    private final AccountRepositoryPort accountRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final SignOutAllUseCase signOutAllUseCase;
    private final SecureHasherPort secureHasher;

    public ResetPasswordService(
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            AccountRepositoryPort accountRepository,
            SignOutAllUseCase signOutAllUseCase,
            PasswordEncoderPort passwordEncoder,
            SecureHasherPort secureHasher
    ) {
        this.recoverPasswordRepository = recoverPasswordRepository;
        this.accountRepository = accountRepository;
        this.signOutAllUseCase = signOutAllUseCase;
        this.passwordEncoder = passwordEncoder;
        this.secureHasher = secureHasher;
    }

    @Override
    public void execute(ResetPasswordCommand command) {
        Instant now = Instant.now();

        String refreshTokenHash = secureHasher.hash(command.recoverToken());

        RecoverPassword recoverPassword = recoverPasswordRepository.findByRecoverTokenHash(refreshTokenHash)
                .orElseThrow(RecoverPasswordNotFoundException::new);

        validateRecoverPassword(recoverPassword, now);

        Account account = recoverPassword.getAccount();

        account.setPasswordHash(passwordEncoder.hash(command.newPassword()));

        recoverPassword.setUsedAt(now);

        accountRepository.save(account);

        signOutAllUseCase.execute(account);

        recoverPasswordRepository.save(recoverPassword);
    }

    private void validateRecoverPassword(RecoverPassword recoverPassword, Instant now) {
        if (!recoverPassword.getExpiresAt().isAfter(now)) {
            throw new RecoverPasswordExpiredException();
        }

        if (recoverPassword.getUsedAt() != null) {
            throw new RecoverPasswordAlreadyUsedException();
        }

        if (recoverPassword.getVerifiedAt() == null) {
            throw new RecoverPasswordNotVerifiedException();
        }
    }
}
