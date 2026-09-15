package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountActivationNotFoundException extends DomainException {
    public AccountActivationNotFoundException() {
        super(DomainErrorCode.ACCOUNT_ACTIVATION_NOT_FOUND);
    }
}
