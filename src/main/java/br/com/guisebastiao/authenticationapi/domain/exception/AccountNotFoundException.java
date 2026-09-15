package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountNotFoundException extends DomainException {
    public AccountNotFoundException() {
        super(DomainErrorCode.ACCOUNT_NOT_FOUND);
    }
}
