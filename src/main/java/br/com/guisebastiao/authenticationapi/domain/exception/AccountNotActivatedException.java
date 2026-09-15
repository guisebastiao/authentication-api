package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountNotActivatedException extends DomainException {
    public AccountNotActivatedException() {
        super(DomainErrorCode.ACCOUNT_NOT_ACTIVATED);
    }
}
