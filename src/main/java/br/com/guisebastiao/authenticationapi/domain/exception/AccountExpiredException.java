package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountExpiredException extends DomainException {
    public AccountExpiredException() {
        super(DomainErrorCode.ACCOUNT_EXPIRED);
    }
}
