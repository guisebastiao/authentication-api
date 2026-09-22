package br.com.guisebastiao.authenticationapi.infrastructure.bean;

import br.com.guisebastiao.authenticationapi.application.port.in.*;
import br.com.guisebastiao.authenticationapi.application.port.out.*;
import br.com.guisebastiao.authenticationapi.application.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public AccountActivateService accountActivateService(
            AccountActivationRepositoryPort accountActivationRepository,
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter
    ) {
        return new AccountActivateService(accountActivationRepository, accountRepository, passwordEncoder, rateLimiter);
    }

    @Bean
    public ChangePasswordService changePasswordService(
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder
    ) {
        return new ChangePasswordService(accountRepository, passwordEncoder);
    }

    @Bean
    public CreateAccountActivationService createAccountActivationService(
            ResendAccountActivationEmailUseCase resendAccountActivationEmail,
            SendAccountActivationEmailUseCase sendAccountActivationEmail,
            AccountActivationRepositoryPort accountActivationRepository,
            SecureRandomGeneratorPort secureRandomGenerator,
            PasswordEncoderPort passwordEncoder,
            SecureHasherPort secureHasher,
            SecureRandom secureRandom
    ) {
        return new CreateAccountActivationService(
                resendAccountActivationEmail,
                sendAccountActivationEmail,
                accountActivationRepository,
                secureRandomGenerator,
                passwordEncoder,
                secureHasher,
                secureRandom
        );
    }

    @Bean
    public CreateAccountService createAccountService(
            CreateAccountActivationUseCase createAccountActivation,
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder,
            RoleRepositoryPort roleRepository,
            RateLimiterPort rateLimiter
    ) {
        return new CreateAccountService(
                createAccountActivation,
                accountRepository,
                passwordEncoder,
                roleRepository,
                rateLimiter
        );
    }

    @Bean
    public CreateRecoverPasswordService createRecoverPasswordService(
            ResendRecoverPasswordEmailService resendRecoverPasswordEmail,
            SendRecoverPasswordEmailUseCase sendRecoverPasswordEmail,
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            AccountRepositoryPort accountRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter,
            SecureRandom secureRandom
    ) {
        return new CreateRecoverPasswordService(
                resendRecoverPasswordEmail,
                sendRecoverPasswordEmail,
                recoverPasswordRepository,
                accountRepository,
                passwordEncoder,
                rateLimiter,
                secureRandom
        );
    }

    @Bean
    public CreateRefreshService createRefreshService(
            SecureRandomGeneratorPort secureRandomGenerator,
            RefreshRepositoryPort refreshRepository,
            SecureHasherPort secureHasher
    ) {
        return new CreateRefreshService(secureRandomGenerator, refreshRepository, secureHasher);
    }

    @Bean
    public CreateSessionService createSessionService(
            SecureRandomGeneratorPort secureRandomGenerator,
            SessionRepositoryPort sessionRepository,
            IpGeolocationPort ipGeolocation,
            SecureHasherPort secureHasher
    ) {
        return new CreateSessionService(secureRandomGenerator, sessionRepository, ipGeolocation, secureHasher);
    }

    @Bean
    public DisableAccountService disableAccountService(
            AccountRepositoryPort accountRepository,
            SignOutAllUseCase signOutAll,
            PasswordEncoderPort passwordEncoder
    ) {
        return new DisableAccountService(accountRepository, signOutAll, passwordEncoder);
    }

    @Bean
    public GetAccountActivationService getAccountActivationService(
            AccountActivationRepositoryPort accountActivationRepository
    ) {
        return new GetAccountActivationService(accountActivationRepository);
    }

    @Bean
    public GetCurrentSessionService getCurrentSessionService(
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher
    ) {
        return new GetCurrentSessionService(sessionRepository, secureHasher);
    }

    @Bean
    public GetSessionsService getSessionsService(
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher
    ) {
        return new GetSessionsService(sessionRepository, secureHasher);
    }

    @Bean
    public GoogleSignUpService googleSignUpService(
            GoogleAuthorizationPort googleAuthorization,
            CreateSessionUseCase createSession,
            CreateRefreshUseCase createRefresh,
            AccountRepositoryPort accountRepository,
            RateLimiterPort rateLimiter,
            JwtTokenPort jwtToken
    ) {
        return new GoogleSignUpService(
                googleAuthorization,
                createSession,
                createRefresh,
                accountRepository,
                rateLimiter,
                jwtToken
        );
    }

    @Bean
    public RefreshTokenService refreshTokenService(
            ValidateSessionUseCase validateSession,
            CreateRefreshUseCase createRefresh,
            RefreshRepositoryPort refreshRepository,
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher,
            JwtTokenPort jwtToken
    ) {
        return new RefreshTokenService(
                validateSession,
                createRefresh,
                refreshRepository,
                sessionRepository,
                secureHasher,
                jwtToken
        );
    }

    @Bean
    public ResendAccountActivationEmailService resendAccountActivationEmailService(
            SendAccountActivationEmailUseCase sendAccountActivationEmail,
            AccountActivationRepositoryPort accountActivationRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter,
            SecureRandom secureRandom
    ) {
        return new ResendAccountActivationEmailService(
                sendAccountActivationEmail,
                accountActivationRepository,
                passwordEncoder,
                rateLimiter,
                secureRandom
        );
    }

    @Bean
    public ResendRecoverPasswordEmailService resendRecoverPasswordEmailService(
            SendRecoverPasswordEmailUseCase sendRecoverPasswordEmail,
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter,
            SecureRandom secureRandom
    ) {
        return new ResendRecoverPasswordEmailService(
                sendRecoverPasswordEmail,
                recoverPasswordRepository,
                passwordEncoder,
                rateLimiter,
                secureRandom
        );
    }

    @Bean
    public ResetPasswordService resetPasswordService(
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            AccountRepositoryPort accountRepository,
            SignOutAllUseCase signOutAll,
            PasswordEncoderPort passwordEncoder
    ) {
        return new ResetPasswordService(recoverPasswordRepository, accountRepository, signOutAll, passwordEncoder);
    }

    @Bean
    public SendAccountActivationEmailService sendAccountActivationEmailService(EmailSenderPort emailSender) {
        return new SendAccountActivationEmailService(emailSender);
    }

    @Bean
    public SendRecoverPasswordEmailService sendRecoverPasswordEmailService(EmailSenderPort emailSender) {
        return new SendRecoverPasswordEmailService(emailSender);
    }

    @Bean
    public SessionSignOutService sessionSignOutService(
            SessionRepositoryPort sessionRepository,
            RefreshRepositoryPort refreshRepository
    ) {
        return new SessionSignOutService(sessionRepository, refreshRepository);
    }

    @Bean
    public SignInService signInService(
            CreateSessionUseCase createSession,
            CreateRefreshUseCase createRefresh,
            AuthenticationPort authentication,
            RateLimiterPort rateLimiter,
            JwtTokenPort jwtToken
    ) {
        return new SignInService(createSession, createRefresh, authentication, rateLimiter, jwtToken);
    }

    @Bean
    public SignOutAllService signOutAllService(
            SessionRepositoryPort sessionRepository,
            RefreshRepositoryPort refreshRepository
    ) {
        return new SignOutAllService(sessionRepository, refreshRepository);
    }

    @Bean
    public SignOutService signOutService(
            SessionRepositoryPort sessionRepository,
            RefreshRepositoryPort refreshRepository,
            SecureHasherPort secureHasher
    ) {
        return new SignOutService(sessionRepository, refreshRepository, secureHasher);
    }

    @Bean
    public ValidateRecoverPasswordService validateRecoverPasswordService(
            RecoverPasswordRepositoryPort recoverPasswordRepository,
            SecureRandomGeneratorPort secureRandomGenerator,
            PasswordEncoderPort passwordEncoder,
            RateLimiterPort rateLimiter
    ) {
        return new ValidateRecoverPasswordService(
                recoverPasswordRepository,
                secureRandomGenerator,
                passwordEncoder,
                rateLimiter
        );
    }

    @Bean
    public ValidateSessionService validateSessionService(
            SessionRepositoryPort sessionRepository,
            SecureHasherPort secureHasher
    ) {
        return new ValidateSessionService(sessionRepository, secureHasher);
    }
}
