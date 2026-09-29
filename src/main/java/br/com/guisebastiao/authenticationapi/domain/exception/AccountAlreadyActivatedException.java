package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountAlreadyActivatedException extends DomainException {
    public AccountAlreadyActivatedException() {
        super(
                DomainErrorCode.ACCOUNT_ALREADY_ACTIVATED,
                "The account is already activated.",
                409
        );
    }
}
