package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountDisabledException extends DomainException {
    public AccountDisabledException() {
        super(DomainErrorCode.ACCOUNT_DISABLED);
    }
}
