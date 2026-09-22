package br.com.guisebastiao.authenticationapi.infrastructure.transaction;

import br.com.guisebastiao.authenticationapi.application.port.in.*;
import br.com.guisebastiao.authenticationapi.application.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Configuration
public class TransactionConfiguration {

    @Bean
    @Primary
    public AccountActivateUseCase transactionalAccountActivateUseCase(
            AccountActivateService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(AccountActivateUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public ChangePasswordUseCase transactionalChangePasswordUseCase(
            ChangePasswordService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(ChangePasswordUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public CreateAccountActivationUseCase transactionalCreateAccountActivationUseCase(
            CreateAccountActivationService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(CreateAccountActivationUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public CreateAccountUseCase transactionalCreateAccountUseCase(
            CreateAccountService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(CreateAccountUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public CreateRecoverPasswordUseCase transactionalCreateRecoverPasswordUseCase(
            CreateRecoverPasswordService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(CreateRecoverPasswordUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public CreateRefreshUseCase transactionalCreateRefreshUseCase(
            CreateRefreshService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(CreateRefreshUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public CreateSessionUseCase transactionalCreateSessionUseCase(
            CreateSessionService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(CreateSessionUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public DisableAccountUseCase transactionalDisableAccountUseCase(
            DisableAccountService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(DisableAccountUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public GetAccountActivationUseCase transactionalGetAccountActivationUseCase(
            GetAccountActivationService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(GetAccountActivationUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public GetCurrentSessionUseCase transactionalGetCurrentSessionUseCase(
            GetCurrentSessionService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(GetCurrentSessionUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public GetSessionsUseCase transactionalGetSessionsUseCase(
            GetSessionsService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(GetSessionsUseCase.class, delegate, transactionTemplate);
    }

    @Bean
    @Primary
    public GoogleSignUpUseCase transactionalGoogleSignUpUseCase(
            GoogleSignUpService delegate,
            TransactionTemplate transactionTemplate
    ) {
        return transactional(GoogleSignUpUseCase.class, delegate, transactionTemplate);
    }

    private <T> T transactional(Class<T> useCaseType, T delegate, TransactionTemplate transactionTemplate) {
        return useCaseType.cast(Proxy.newProxyInstance(
                useCaseType.getClassLoader(),
                new Class<?>[]{useCaseType},
                (proxy, method, args) -> transactionTemplate.execute(status -> invoke(delegate, method, args))
        ));
    }

    private Object invoke(Object delegate, Method method, Object[] args) {
        try {
            return method.invoke(delegate, args);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();

            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }

            if (cause instanceof Error error) {
                throw error;
            }

            throw new IllegalStateException(cause);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("Unable to invoke use case", exception);
        }
    }
}
