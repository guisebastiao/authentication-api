package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountActivationTokenInvalidException extends DomainException {
    public AccountActivationTokenInvalidException() {
        super(DomainErrorCode.ACCOUNT_ACTIVATION_TOKEN_INVALID);
    }
}
