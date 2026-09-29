package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.command.SignInCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateAccountActivationUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateRefreshUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.CreateSessionUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.SignInUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.AuthenticationPort;
import br.com.guisebastiao.authenticationapi.application.port.out.JwtTokenPort;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import br.com.guisebastiao.authenticationapi.application.result.CreateRefreshResult;
import br.com.guisebastiao.authenticationapi.application.result.CreateSessionResult;
import br.com.guisebastiao.authenticationapi.domain.enums.AccountStatus;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountInvalidCredentialsException;
import br.com.guisebastiao.authenticationapi.domain.exception.AccountNotActivatedException;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public class SignInService implements SignInUseCase {
    private final CreateAccountActivationUseCase createAccountActivationUseCase;
    private final CreateSessionUseCase createSessionUseCase;
    private final CreateRefreshUseCase createRefreshUseCase;
    private final AccountRepositoryPort accountRepository;
    private final AuthenticationPort authenticationPort;
    private final JwtTokenPort jwtToken;

    public SignInService(
            CreateAccountActivationUseCase createAccountActivationUseCase,
            CreateSessionUseCase createSessionUseCase,
            CreateRefreshUseCase createRefreshUseCase,
            AccountRepositoryPort accountRepository,
            AuthenticationPort authenticationPort,
            JwtTokenPort jwtToken
    ) {
        this.createAccountActivationUseCase = createAccountActivationUseCase;
        this.createSessionUseCase = createSessionUseCase;
        this.createRefreshUseCase = createRefreshUseCase;
        this.authenticationPort = authenticationPort;
        this.accountRepository = accountRepository;
        this.jwtToken = jwtToken;
    }

    @Override
    public AuthResult execute(SignInCommand command, String userAgent, String ipAddress) {
        Account account = accountRepository.findByEmail(command.email())
                .orElseThrow(AccountInvalidCredentialsException::new);

        if (account.getStatus() == AccountStatus.PENDING) {
            AccountActivationResult data = createAccountActivationUseCase.execute(account);

            throw new AccountNotActivatedException(data);
        }

        authenticationPort.authenticate(command.email(), command.password());

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
