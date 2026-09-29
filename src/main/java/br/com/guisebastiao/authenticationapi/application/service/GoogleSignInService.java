package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.GoogleSignInCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateAccountActivationUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateRefreshUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.GoogleSignInUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.*;
import br.com.guisebastiao.authenticationapi.application.result.*;
import br.com.guisebastiao.authenticationapi.domain.enums.AccountStatus;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountDisabledException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountNotActivatedException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountNotFoundException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public class GoogleSignInService implements GoogleSignInUseCase {
    private final CreateAccountActivationUseCase createAccountActivationUseCase;
    private final GoogleAuthorizationPort googleAuthorization;
    private final CreateSessionUseCase createSessionUseCase;
    private final CreateRefreshUseCase createRefreshUseCase;
    private final AccountRepositoryPort accountRepository;
    private final JwtTokenPort jwtToken;

    public GoogleSignInService(
            CreateAccountActivationUseCase createAccountActivationUseCase,
            GoogleAuthorizationPort googleAuthorization,
            CreateSessionUseCase createSessionUseCase,
            CreateRefreshUseCase createRefreshUseCase,
            AccountRepositoryPort accountRepository,
            JwtTokenPort jwtToken
    ) {
        this.createAccountActivationUseCase = createAccountActivationUseCase;
        this.createSessionUseCase = createSessionUseCase;
        this.createRefreshUseCase = createRefreshUseCase;
        this.googleAuthorization = googleAuthorization;
        this.accountRepository = accountRepository;
        this.jwtToken = jwtToken;
    }

    @Override
    public AuthResult execute(GoogleSignInCommand command, String userAgent, String ipAddress) {
        GoogleAuthorizationResult googleResult = googleAuthorization.authorize(command.credential());

        Account account = accountRepository.findByEmail(googleResult.email())
                .orElseThrow(AccountNotFoundException::new);

        if (account.getStatus() == AccountStatus.PENDING) {
            AccountActivationResult data = createAccountActivationUseCase.execute(account);

            throw new AccountNotActivatedException(data);
        }

        if (account.getStatus() == AccountStatus.DISABLED) {
            throw new AccountDisabledException();
        }

        CreateSessionResult sessionResult = createSessionUseCase.execute(
                account,
                userAgent,
                ipAddress
        );

        CreateRefreshResult refreshResult = createRefreshUseCase.execute(sessionResult.session());

        String accessToken = jwtToken.generate(account, sessionResult.sessionToken());

        return new AuthResult(accessToken, refreshResult.refreshToken(), sessionResult.sessionToken());
    }
}
