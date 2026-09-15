package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountActivationTokenExpiredException extends DomainException {
    public AccountActivationTokenExpiredException() {
        super(DomainErrorCode.ACCOUNT_ACTIVATION_TOKEN_EXPIRED);
    }
}
