package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountEmailAlreadyInUseException extends DomainException {
    public AccountEmailAlreadyInUseException() {
        super(DomainErrorCode.ACCOUNT_EMAIL_ALREADY_IN_USE);
    }
}
