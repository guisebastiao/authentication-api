package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountInvalidCredentialsException extends DomainException {
    public AccountInvalidCredentialsException() {
        super(DomainErrorCode.ACCOUNT_INVALID_CREDENTIALS);
    }
}
