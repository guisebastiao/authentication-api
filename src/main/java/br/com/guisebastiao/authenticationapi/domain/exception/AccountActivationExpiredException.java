package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountActivationExpiredException extends DomainException {
    public AccountActivationExpiredException() {
        super(DomainErrorCode.ACCOUNT_ACTIVATION_EXPIRED);
    }
}
