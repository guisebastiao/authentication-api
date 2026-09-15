package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountAlreadyExistsException extends DomainException {
    public AccountAlreadyExistsException() {
        super(DomainErrorCode.ACCOUNT_ALREADY_EXISTS);
    }
}
